package com.ahmedtawfik.moviesapp.model.remote.network

import com.ahmedtawfik.moviesapp.model.entity.*

interface RemoteRepository {
    suspend fun getPopularMovies(): Result<MoviesResponse>
    suspend fun getUpcomingMovies(): Result<UpcomingMoviesResponse>
    suspend fun getTopRatedMovies(): Result<MoviesResponse>
    suspend fun getMovieDetails(movie_id: Int): Result<MovieOverview>
    suspend fun getCredits(movie_id: Int): Result<Credit>
    suspend fun getSimilarMovies(movie_id: Int): Result<MoviesResponse>
    suspend fun rateMovie(
        movie_id: Int,
        guest_session_id: String,
        value: Float
    ): Result<RatingResponse>

    suspend fun getGuestSessionId(): Result<GuestSession>
}