package com.ahmedtawfik.moviesapp.model.remote.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/***
 * @author Eng. Ahmed Tawfik
 *
 * The implementation of {@link RemoteRepository }
 */
class RemoteRepositoryImp(private val apiService: APIService) : RemoteRepository {
    override suspend fun getPopularMovies(
    ) = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getPopularMovies())
    }

    override suspend fun getUpcomingMovies() = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getUpcomingMovies())
    }

    override suspend fun getTopRatedMovies() = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getTopRatedMovies())
    }

    override suspend fun getMovieDetails(movie_id: Int) = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getMovieDetails(movie_id))
    }

    override suspend fun getCredits(movie_id: Int) = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getCredits(movie_id))
    }

    override suspend fun getSimilarMovies(movie_id: Int) = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getSimilarMovies(movie_id))
    }

    override suspend fun rateMovie(movie_id: Int, guest_session_id: String, value: Float) =
        withContext(Dispatchers.IO) {
            safeApiResult(apiService.rateMovie(movie_id, guest_session_id, value))
        }

    override suspend fun getGuestSessionId() = withContext(Dispatchers.IO) {
        safeApiResult(apiService.getGuestSessionId())
    }


    private fun <T> safeApiResult(call: Response<T>): Result<T> {
        if (call.isSuccessful) return Result.Success(call.body())
        when (call.code()) {
            500, 504 -> throw Failure.ServerException(call.message())
            404 -> throw Failure.NotFoundException(call.message())
            401 -> throw Failure.InvalidAPIKeyException(call.message())
            else -> {
                return Result.Error(Failure.UnknownException(call.message()))
            }
        }
    }

}