package com.ahmedtawfik.moviesapp.ui.upcomingmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.entity.UpcomingMoviesResponse
import com.ahmedtawfik.moviesapp.model.remote.Repository
import com.ahmedtawfik.moviesapp.model.remote.network.Result
import com.ahmedtawfik.moviesapp.ui.base.BaseViewModel
import com.ahmedtawfik.moviesapp.utils.NetworkState
import kotlinx.coroutines.launch

class UpcomingMoviesViewModel(private val repository: Repository) : BaseViewModel(repository) {

    private var upcomingMoviesMLiveData = MutableLiveData<List<Movie>>()

    val upcomingMoviesLiveData: LiveData<List<Movie>> get() = upcomingMoviesMLiveData

    fun getUpcomingMovies() {
        viewModelScope.launch(handler) {
            networkStatus.postValue(NetworkState.LOADED)
            setResult(repository.getUpcomingMovies())
        }
    }

    private fun setResult(moviesResult: Result<UpcomingMoviesResponse>) {
        when (moviesResult) {
            is Result.Success -> {
                if (moviesResult.data != null) {
                    upcomingMoviesMLiveData.postValue(moviesResult.data.results)
                }
            }
            is Result.Error -> {
                networkStatus.postValue(NetworkState.error("Failed to get data"))
            }
        }
    }
}