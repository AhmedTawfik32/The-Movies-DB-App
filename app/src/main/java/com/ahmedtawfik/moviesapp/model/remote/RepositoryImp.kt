package com.ahmedtawfik.moviesapp.model.remote

import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.local.roomdb.DatabaseRepository
import com.ahmedtawfik.moviesapp.model.local.sharedpref.SharedPreferenceHelper
import com.ahmedtawfik.moviesapp.model.remote.network.RemoteRepository

/***
 * @author Eng. Ahmed Tawfik
 *
 * The implementation of {@link Repository }
 */
class RepositoryImp(
    private val remoteRepository: RemoteRepository,
    private val sharedPreferenceRepository: SharedPreferenceHelper,
    private val databaseRepository: DatabaseRepository
) : Repository {
    override suspend fun getPopularMovies() = remoteRepository.getPopularMovies()
    override suspend fun getUpcomingMovies() = remoteRepository.getUpcomingMovies()
    override suspend fun getTopRatedMovies() = remoteRepository.getTopRatedMovies()
    override suspend fun getMovieDetails(movie_id: Int) = remoteRepository.getMovieDetails(movie_id)
    override suspend fun getCredits(movie_id: Int) = remoteRepository.getCredits(movie_id)
    override suspend fun getSimilarMovies(movie_id: Int) =
        remoteRepository.getSimilarMovies(movie_id)

    override suspend fun rateMovie(movie_id: Int, guest_session_id: String, value: Float) =
        remoteRepository.rateMovie(movie_id, guest_session_id, value)

    override suspend fun getGuestSessionId() = remoteRepository.getGuestSessionId()
    override fun getGuestSession() = sharedPreferenceRepository.getGuestSession()
    override fun setGuestSession(guestSessionId: String) {
        sharedPreferenceRepository.setGuestSession(guestSessionId)
    }

    override fun getGuestSessionExpiration() =
        sharedPreferenceRepository.getGuestSessionExpiration()

    override fun setGuestSessionExpiration(guestSessionExpiration: String) {
        sharedPreferenceRepository.setGuestSessionExpiration(guestSessionExpiration)
    }

    override fun addFavToMovie(movieId: String, isFav: Boolean) {
        sharedPreferenceRepository.addFavToMovie(movieId, isFav)
    }

    override fun isFavMovie(movieId: String) = sharedPreferenceRepository.isFavMovie(movieId)
    override suspend fun addMovie(movie: Movie) {
        databaseRepository.addMovie(movie)
    }

    override suspend fun fetchAllMovies() = databaseRepository.fetchAllMovies()
    override suspend fun deleteMovie(movie: Movie) {
        databaseRepository.deleteMovie(movie)
    }
}