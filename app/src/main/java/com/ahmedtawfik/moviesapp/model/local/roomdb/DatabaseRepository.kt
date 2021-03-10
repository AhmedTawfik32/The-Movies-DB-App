package com.ahmedtawfik.moviesapp.model.local.roomdb

import com.ahmedtawfik.moviesapp.model.entity.Movie

/***
 * @author Ahmed Tawfik
 *
 * Interface to interact with room database
 */
interface DatabaseRepository {
    suspend fun addMovie(movie: Movie)
    suspend fun fetchAllMovies(): List<Movie>
    suspend fun deleteMovie(movie: Movie)
}