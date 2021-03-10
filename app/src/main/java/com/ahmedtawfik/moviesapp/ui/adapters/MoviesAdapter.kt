package com.ahmedtawfik.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.entity.OnMovieClickListener
import com.ahmedtawfik.moviesapp.utils.GetImageURL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_movie.view.*
import kotlin.properties.Delegates

class MoviesAdapter :
    RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies: List<Movie> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onMovieClickListener: OnMovieClickListener? = null


    fun setMoviesList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_movie, parent, false)
        return MoviesViewHolder(v, onMovieClickListener)
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        var movie: Movie? = movies.get(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}

class MoviesViewHolder(
    v: View,
    private val onMovieClickListener: OnMovieClickListener?
) : RecyclerView.ViewHolder(v) {

    fun bind(movie: Movie) {
        itemView.run {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

            tv_movieName.text = movie.title.toString()
            tv_productionDate.text = movie.release_date.toString()
            tv_rating.text = movie.vote_average.toString()

            Glide.with(this).load(GetImageURL.getImageFullURL(movie.poster_path.toString()))
                .fitCenter()
                .apply(requestOptions)
                .placeholder(R.drawable.ic_baseline_image)
                .into(iv_moviePoster)

            setOnClickListener {
                onMovieClickListener?.onItemClicked(movie)
            }
        }
    }

}
