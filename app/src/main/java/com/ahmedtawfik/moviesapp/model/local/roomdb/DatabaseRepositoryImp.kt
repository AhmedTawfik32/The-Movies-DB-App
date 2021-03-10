package com.ahmedtawfik.moviesapp.model.local.roomdb

import com.ahmedtawfik.moviesapp.model.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/***
 * @author Eng. Ahmed Tawfik
 *
 * The implementation of {@link DatabaseRepository }
 */
class DatabaseRepositoryImp(private val db: MovieDatabase) : DatabaseRepository {
    override suspend fun addMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            db.movieDAO().addMovie(movie)
        }
    }

    override suspend fun fetchAllMovies() = withContext(Dispatchers.IO) {
        db.movieDAO().fetchAllMovies()
    }

    override suspend fun deleteMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            db.movieDAO().deleteMovie(movie)
        }
    }
}