package com.ahmedtawfik.moviesapp.ui.topratedmovies

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

class TopRatedMoviesViewModel(private val repository: Repository) : BaseViewModel(repository) {
    private var topRatedMoviesMLiveData = MutableLiveData<List<Movie>>()

    val topRatedMoviesLiveData: LiveData<List<Movie>> get() = topRatedMoviesMLiveData

    fun getTopRatedMovies() {
        viewModelScope.launch(handler) {
            networkStatus.postValue(NetworkState.LOADED)
            setResult(repository.getTopRatedMovies())
        }
    }

    private fun setResult(moviesResult: Result<MoviesResponse>) {
        when (moviesResult) {
            is Result.Success -> {
                if (moviesResult.data != null) {
                    topRatedMoviesMLiveData.postValue(moviesResult.data.results)
                }
            }
            is Result.Error -> {
                networkStatus.postValue(NetworkState.error("Failed to get data"))
            }
        }
    }
}