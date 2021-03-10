package com.ahmedtawfik.moviesapp.ui.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.remote.Repository
import com.ahmedtawfik.moviesapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(private val repository: Repository) : BaseViewModel(repository) {
    private var fetchFavMoviesMLivedata = MutableLiveData<List<Movie>>()

    val fetchFavMoviesLiveData: LiveData<List<Movie>> get() = fetchFavMoviesMLivedata

    fun fetchAllFavMovies() = viewModelScope.launch {
        fetchFavMoviesMLivedata.postValue(repository.fetchAllMovies())
    }
}