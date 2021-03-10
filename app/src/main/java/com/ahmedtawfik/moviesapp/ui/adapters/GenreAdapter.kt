package com.ahmedtawfik.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.model.entity.Genre
import kotlinx.android.synthetic.main.item_list_genre.view.*
import kotlin.properties.Delegates

class GenreAdapter : RecyclerView.Adapter<GenreViewHolder>() {

    var genres: List<Genre> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateGenres(mGenres: List<Genre>) {
        this.genres = mGenres
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

}

class GenreViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(genre: Genre) {
        itemView.run {
            if (genre != null) {
                tv_genreName_genre.text = genre.name
            }
        }
    }
}
