package com.ahmedtawfik.moviesapp.ui.popularmovies

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
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.show_error_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PopularMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopularMoviesFragment :
    BaseFragment<PopularMoviesViewModel>(PopularMoviesViewModel::class), OnMovieClickListener {

    override val viewModel: PopularMoviesViewModel by viewModel()

    private val popularMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.mPopularMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (!it.isNullOrEmpty()) {
                    popularMoviesAdapter.setMoviesList(it)
                    hideLoading()
                }
            })
    }

    override fun onItemClicked(movie: Movie) {
        var action = PopularMoviesFragmentDirections.actionToMovieOverviewFragment(movie)
        findNavController().navigate(action)
    }

    override fun hideLoading() {
        pb_error_layout.visibility = View.GONE
        rv_popularMovies.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pb_error_layout.visibility = View.VISIBLE
        rv_popularMovies.visibility = View.GONE
    }

    override fun initViews() {
        viewModel.retrievePopularMovies()
        rv_popularMovies.adapter = popularMoviesAdapter
        popularMoviesAdapter.onMovieClickListener = this
    }
}


