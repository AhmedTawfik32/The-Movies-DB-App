package com.ahmedtawfik.moviesapp.model.local.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.ahmedtawfik.moviesapp.R

/***
 * @author Eng. Ahmed Tawfik
 *
 * The implementation of {@link SharedPreferenceHelper }
 */
class SharedPreferenceHelperImp(private val context: Context) : SharedPreferenceHelper {

    companion object {
        private const val GUEST_SESSION_ID_PREF = "guestSessionId"
        private const val GUEST_SESSION_EXPIRATION_PREF = "guestSessionExpiration"
    }

    private val prefmng: SharedPreferences by lazy {
        context.getSharedPreferences(R.string.PREFERENCE_FILE_KEY.toString(), Context.MODE_PRIVATE)
    }

    override fun getGuestSession() = getPreferencesString(GUEST_SESSION_ID_PREF)

    override fun setGuestSession(guestSessionId: String) {
        setPreferencesString(GUEST_SESSION_ID_PREF, guestSessionId)
    }

    override fun getGuestSessionExpiration() = getPreferencesString(GUEST_SESSION_EXPIRATION_PREF)

    override fun setGuestSessionExpiration(guestSessionExpiration: String) {
        setPreferencesString(GUEST_SESSION_EXPIRATION_PREF, guestSessionExpiration)
    }

    override fun addFavToMovie(movieId: String, isFav: Boolean) {
        setPreferencesBoolean(movieId, isFav)
    }

    override fun isFavMovie(movieId: String): Boolean {
        return getPreferencesBoolean(movieId)
    }

    private fun getPreferencesString(guestSessionIdPref: String): String? {
        return prefmng.getString(guestSessionIdPref, null)
    }

    private fun setPreferencesString(guestSessionIdPref: String, guestSessionId: String) {
        with(prefmng.edit()) {
            putString(guestSessionIdPref, guestSessionId)
            apply()
        }
    }

    private fun getPreferencesBoolean(movieIdPref: String): Boolean {
        return prefmng.getBoolean(movieIdPref, false)
    }

    private fun setPreferencesBoolean(movieIdPref: String, isFav: Boolean) {
        with(prefmng.edit()) {
            putBoolean(movieIdPref, isFav)
            apply()
        }
    }

}