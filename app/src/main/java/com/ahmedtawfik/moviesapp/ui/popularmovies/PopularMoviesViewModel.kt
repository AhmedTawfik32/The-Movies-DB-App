package com.ahmedtawfik.moviesapp.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.entity.MoviesResponse
import com.ahmedtawfik.moviesapp.model.remote.Repository
import com.ahmedtawfik.moviesapp.model.remote.network.Result
import com.ahmedtawfik.moviesapp.ui.base.BaseViewModel
import com.ahmedtawfik.moviesapp.utils.NetworkState
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val repository: Repository) : BaseViewModel(repository) {


    var mPopularMoviesMLiveData = MutableLiveData<List<Movie>>()

    val mPopularMoviesLiveData: LiveData<List<Movie>> get() = mPopularMoviesMLiveData

    fun retrievePopularMovies() {
        viewModelScope.launch(handler) {
            networkStatus.postValue(NetworkState.LOADING)
            repository.getPopularMovies().let { setResults(it) }
        }
    }

    private fun setResults(moviesResult: Result<MoviesResponse>) {
        when (moviesResult) {
            is Result.Success -> {
                if (moviesResult.data != null) {
                    mPopularMoviesMLiveData.postValue(moviesResult.data.results)
                }
            }
            else -> networkStatus.postValue(NetworkState.error("Failed to get data"))
        }
    }
}