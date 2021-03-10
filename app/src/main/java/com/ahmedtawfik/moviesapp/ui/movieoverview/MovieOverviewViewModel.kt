package com.ahmedtawfik.moviesapp.ui.movieoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmedtawfik.moviesapp.model.entity.*
import com.ahmedtawfik.moviesapp.model.remote.Repository
import com.ahmedtawfik.moviesapp.model.remote.network.Result
import com.ahmedtawfik.moviesapp.ui.base.BaseViewModel
import com.ahmedtawfik.moviesapp.utils.NetworkState
import kotlinx.coroutines.launch

class MovieOverviewViewModel(private val repository: Repository, private val movie_id: Int) :
    BaseViewModel(repository) {

    private var movieDetailsMLiveData = MutableLiveData<MovieOverview>()

    val movieDetailsLiveData: LiveData<MovieOverview> get() = movieDetailsMLiveData

    private var movieCreditsMLiveData = MutableLiveData<Credit>()

    val movieCreditsLiveData: LiveData<Credit> get() = movieCreditsMLiveData

    private var similarMoviesMLivedata = MutableLiveData<MoviesResponse>()

    val similarMoviesLiveData: LiveData<MoviesResponse> get() = similarMoviesMLivedata

    private var rateMoviesMLivedata = MutableLiveData<RatingResponse>()

    val rateMoviesLiveData: LiveData<RatingResponse> get() = rateMoviesMLivedata


    fun getMovieOverviewDetails() {
        viewModelScope.launch(handler) {
            networkStatus.postValue(NetworkState.LOADING)
            setResult(repository.getMovieDetails(movie_id))
        }
    }

    fun getCredits() {
        viewModelScope.launch(handler) {
            networkStatus.postValue(NetworkState.LOADING)
            setResultForCredit(repository.getCredits(movie_id))
        }
    }

    fun getSimilarMovies() {
        viewModelScope.launch(handler) {
            setResultForSimilarMovies(repository.getSimilarMovies(movie_id))
        }
    }

    fun rateMovie(guest_session_id: String, value: Float) {
        viewModelScope.launch(handler) {
            setResultForRatingMovie(repository.rateMovie(movie_id, guest_session_id, value))
        }
    }

    fun addFavMovie(movie: Movie) {
        viewModelScope.launch(handler) {
            repository.addMovie(movie)
        }
    }

    fun deleteFavMovie(movie: Movie) {
        viewModelScope.launch(handler) {
            repository.deleteMovie(movie)
        }
    }

    fun addMovieToFavorites(movie_id: Int, isFav: Boolean) {
        repository.addFavToMovie(movie_id.toString(), isFav)
    }

    fun isFavMovie(movie_id: Int): Boolean {
        return repository.isFavMovie(movie_id.toString())
    }

    private fun setResultForRatingMovie(rateMovie: Result<RatingResponse>) {
        when (rateMovie) {
            is Result.Success -> {
                if (rateMovie.data != null) {
                    networkStatus.postValue(NetworkState.LOADED)
                    rateMoviesMLivedata.postValue(rateMovie.data)
                }
            }
            is Result.Error -> {
                networkStatus.postValue(NetworkState.error("Failed to post rate"))
            }
        }
    }

    private fun setResultForSimilarMovies(similarMovies: Result<MoviesResponse>) {
        when (similarMovies) {
            is Result.Success -> {
                if (similarMovies.data != null) {
                    networkStatus.postValue(NetworkState.LOADED)
                    similarMoviesMLivedata.postValue(similarMovies.data)
                }
            }
            is Result.Error -> {
                networkStatus.postValue(NetworkState.error("Failed to get data"))
            }
        }
    }

    private fun setResult(result: Result<MovieOverview>) {
        when (result) {
            is Result.Success -> {
                if (result.data != null)
                    networkStatus.postValue(NetworkState.LOADED)
                movieDetailsMLiveData.postValue(result.data)
            }
            is Result.Error -> {
                networkStatus.postValue(NetworkState.error("Data Not found"))
            }
        }
    }

    private fun setResultForCredit(result: Result<Credit>) {
        when (result) {
            is Result.Success -> {
                if (result.data != null)
                    networkStatus.postValue(NetworkState.LOADED)
                movieCreditsMLiveData.postValue(result.data)
            }
            is Result.Error -> {
                networkStatus.postValue(NetworkState.error("Data Not found"))
            }
        }
    }

}