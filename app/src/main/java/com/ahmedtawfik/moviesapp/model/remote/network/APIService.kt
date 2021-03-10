package com.ahmedtawfik.moviesapp.model.remote.network

import com.ahmedtawfik.moviesapp.model.entity.*
import retrofit2.Response
import retrofit2.http.*


interface APIService {

    /***
     * see https://developers.themoviedb.org/3/movies/get-popular-movies
     * Get a list of the current popular movies on TMDb. This list updates daily.
     *
     */
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
    ): Response<MoviesResponse>

    /***
     * see https://developers.themoviedb.org/3/movies/get-upcoming
     * Get a list of upcoming movies in theatres.
     * This is a release type query that looks for all movies
     * that have a release type of 2 or 3 within the specified date range.
     */
    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
    ): Response<UpcomingMoviesResponse>

    /***
     * see https://developers.themoviedb.org/3/movies/get-top-rated-movies
     * Get the top rated movies on TMDb.
     *
     */
    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
    ): Response<MoviesResponse>

    /***
     * see https://developers.themoviedb.org/3/movies/get-movie-details
     * Get the primary information about a movie.
     *
     * @param [movie_id] the id of movie
     */
    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): Response<MovieOverview>

    /***
     * see https://developers.themoviedb.org/3/movies/get-movie-credits
     * Get the cast and crew for a movie.
     *
     * @param [movie_id] the id of movie
     */
    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Int
    ): Response<Credit>

    /***
     * see https://developers.themoviedb.org/3/movies/get-similar-movies
     * Get a list of similar movies.
     * This is not the same as the "Recommendation" system you see on the website.
     * These items are assembled by looking at keywords and genres.
     *
     * @param [movie_id] the id of movie
     */
    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: Int
    ): Response<MoviesResponse>

    /***
     * see https://developers.themoviedb.org/3/movies/rate-movie
     * Rate a movie. A valid session or guest session ID is required.
     *
     * @param [movie_id] the id of movie
     */
    @POST("/3/movie/{movie_id}/rating")
    suspend fun rateMovie(
        @Path("movie_id") movie_id: Int,
        @Query("guest_session_id") guest_session_id: String,
        @Body value: Float
    ): Response<RatingResponse>

    /***
     * see https://developers.themoviedb.org/3/authentication/create-guest-session
     * This method will let you create a new guest session.
     * Guest sessions are a type of session that will let a user rate movies
     * but not require them to have a TMDb user account.
     */
    @GET("/3/authentication/guest_session/new")
    suspend fun getGuestSessionId(): Response<GuestSession>
}