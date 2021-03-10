package com.ahmedtawfik.moviesapp.ui.movieoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.entity.OnMovieClickListener
import com.ahmedtawfik.moviesapp.ui.adapters.CastAdapter
import com.ahmedtawfik.moviesapp.ui.adapters.GenreAdapter
import com.ahmedtawfik.moviesapp.ui.adapters.MoviesAdapter
import com.ahmedtawfik.moviesapp.ui.adapters.ProductionCompanyAdapter
import com.ahmedtawfik.moviesapp.ui.base.BaseFragment
import com.ahmedtawfik.moviesapp.utils.GetImageURL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_movie_overview.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [MovieOverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieOverviewFragment : BaseFragment<MovieOverviewViewModel>(MovieOverviewViewModel::class),
    OnMovieClickListener {
    // TODO: Rename and change types of parameters

    private val fragArgs: MovieOverviewFragmentArgs by navArgs()


    override val viewModel: MovieOverviewViewModel by viewModel {
        parametersOf(fragArgs.mMovie.id)
    }

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter()
    }

    private val castAdapter: CastAdapter by lazy {
        CastAdapter()
    }

    private val productionCompanyAdapter: ProductionCompanyAdapter by lazy {
        ProductionCompanyAdapter()
    }

    private val similarMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_overview, container, false)
    }

    override fun hideLoading() {
        pb_movie_overview.visibility = View.GONE
    }

    override fun showLoading() {
        pb_movie_overview.visibility = View.VISIBLE
    }

    override fun initViews() {
        checkIsFavMovie()
        viewModel.getMovieOverviewDetails()
        viewModel.getCredits()
        viewModel.getSimilarMovies()
        similarMoviesAdapter.onMovieClickListener = this
        btn_fav.setOnClickListener { v ->
            if (v.tag == R.string.Tag_Empty) {
                viewModel.addFavMovie(fragArgs.mMovie)
                v.setBackgroundResource(R.drawable.ic_fav)
                viewModel.addMovieToFavorites(fragArgs.mMovie.id, true)
                v.tag = R.string.Tag_Yellow
            } else {
                viewModel.deleteFavMovie(fragArgs.mMovie)
                v.setBackgroundResource(R.drawable.ic_fav_empty)
                viewModel.addMovieToFavorites(fragArgs.mMovie.id, false)
                v.tag = R.string.Tag_Empty
            }
        }
        ratingBar_overview.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            var guestSessionID = viewModel.getGuestSessionID().toString()
            viewModel.rateMovie(guestSessionID, (rating * 2.0).toFloat())
        }
    }

    private fun checkIsFavMovie() {
        var isFav = viewModel.isFavMovie(fragArgs.mMovie.id)
        if (isFav) {
            btn_fav.setBackgroundResource(R.drawable.ic_fav)
            btn_fav.tag = R.string.Tag_Yellow
        } else {
            btn_fav.setBackgroundResource(R.drawable.ic_fav_empty)
            btn_fav.tag = R.string.Tag_Empty
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(requireView()).load(GetImageURL.getImageFullURL(it.poster_path.toString()))
                .fitCenter().apply(requestOptions)
                .placeholder(R.drawable.ic_baseline_image).into(iv_moviePoster_overview)
            tv_movieName_overview.text = it.original_title
            tv_duration_overview.text = it.runtime.toString()
            tv_overview_text.text = it.overview
            tv_movieProductionDate_overview.text = it.release_date.toString()
            tv_duration_overview.text = it.runtime.toString() + " mins"
            tv_rating_overview.text = it.vote_average.toString()
            rv_genre_overview.adapter = genreAdapter
            genreAdapter.updateGenres(it.genres)
            rv_production_company.adapter = productionCompanyAdapter
            productionCompanyAdapter.updateProductionCompany(it.production_companies)

        })
        viewModel.movieCreditsLiveData.observe(viewLifecycleOwner, {
            rv_cast_overview.adapter = castAdapter
            castAdapter.updateCast(it.cast)
        })
        viewModel.similarMoviesLiveData.observe(viewLifecycleOwner, {
            rv_similarMovies_overview.adapter = similarMoviesAdapter
            similarMoviesAdapter.setMoviesList(it.results)
        })

        viewModel.rateMoviesLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                "You are successfully rate this movie\n" + it.status_message,
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    override fun onItemClicked(movie: Movie) {
        var action = MovieOverviewFragmentDirections.actionMovieOverviewFragmentSelf(movie)
        findNavController().navigate(action)
    }
}