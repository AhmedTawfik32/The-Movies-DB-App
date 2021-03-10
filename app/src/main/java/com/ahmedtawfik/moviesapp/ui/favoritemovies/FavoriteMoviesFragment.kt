package com.ahmedtawfik.moviesapp.ui.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.entity.OnMovieClickListener
import com.ahmedtawfik.moviesapp.ui.adapters.MoviesAdapter
import com.ahmedtawfik.moviesapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite_movies.*
import kotlinx.android.synthetic.main.show_error_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteMoviesFragment :
    BaseFragment<FavoriteMoviesViewModel>(FavoriteMoviesViewModel::class), OnMovieClickListener {

    override val viewModel: FavoriteMoviesViewModel by viewModel()

    private val favMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.fetchFavMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (!it.isNullOrEmpty()) {
                    favMoviesAdapter.setMoviesList(it)
                    hideLoading()
                }
            })
    }

    override fun onItemClicked(movie: Movie) {
        var action =
            FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieOverviewFragment(
                movie
            )
        findNavController().navigate(action)
    }

    override fun hideLoading() {
        pb_error_layout.visibility = View.GONE
        rv_favMovies.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pb_error_layout.visibility = View.VISIBLE
        rv_favMovies.visibility = View.GONE
    }

    override fun initViews() {
        viewModel.fetchAllFavMovies()
        rv_favMovies.adapter = favMoviesAdapter
        favMoviesAdapter.onMovieClickListener = this
    }
}