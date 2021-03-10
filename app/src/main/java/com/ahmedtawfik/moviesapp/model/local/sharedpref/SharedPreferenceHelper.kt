package com.ahmedtawfik.moviesapp.model.local.sharedpref

/***
 * @author Ahmed Tawfik
 *
 * Interface to interact with shared preferences
 */
interface SharedPreferenceHelper {
    fun getGuestSession(): String?
    fun setGuestSession(guestSessionId: String)
    fun getGuestSessionExpiration(): String?
    fun setGuestSessionExpiration(guestSessionExpiration: String)
    fun addFavToMovie(movieId: String, isFav: Boolean)
    fun isFavMovie(movieId: String): Boolean
}