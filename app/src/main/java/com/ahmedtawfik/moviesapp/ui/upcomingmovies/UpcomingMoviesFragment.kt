package com.ahmedtawfik.moviesapp.ui.upcomingmovies

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
import kotlinx.android.synthetic.main.fragment_upcoming_movies.*
import kotlinx.android.synthetic.main.show_error_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [UpcomingMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpcomingMoviesFragment :
    BaseFragment<UpcomingMoviesViewModel>(UpcomingMoviesViewModel::class), OnMovieClickListener {

    override val viewModel: UpcomingMoviesViewModel by viewModel()

    private val upcomingMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_movies, container, false)
    }

    override fun hideLoading() {
        pb_error_layout.visibility = View.GONE
        rv_upcomingMovies.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pb_error_layout.visibility = View.VISIBLE
        rv_upcomingMovies.visibility = View.GONE
    }

    override fun initViews() {
        viewModel.getUpcomingMovies()
        rv_upcomingMovies.adapter = upcomingMoviesAdapter
        upcomingMoviesAdapter.onMovieClickListener = this
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.upcomingMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (!it.isNullOrEmpty()) {
                    upcomingMoviesAdapter.setMoviesList(it)
                    hideLoading()
                }
            })
    }

    override fun onItemClicked(movie: Movie) {
        var action =
            UpcomingMoviesFragmentDirections.actionUpcomingMoviesFragmentToMovieOverviewFragment(
                movie
            )
        findNavController().navigate(action)
    }
}