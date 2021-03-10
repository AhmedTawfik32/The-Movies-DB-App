package com.ahmedtawfik.moviesapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedtawfik.moviesapp.model.remote.Repository
import com.ahmedtawfik.moviesapp.model.remote.network.Failure
import com.ahmedtawfik.moviesapp.model.remote.network.Result
import com.ahmedtawfik.moviesapp.utils.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

open class BaseViewModel(private val repository: Repository) : ViewModel() {

    internal var networkStatus = MutableLiveData<NetworkState>()
    val mNetworkState: LiveData<NetworkState> get() = networkStatus

    internal var handler = CoroutineExceptionHandler { _, ex ->
        when (ex) {
            is Failure.InvalidAPIKeyException -> {
                networkStatus.postValue(NetworkState.error(ex.message ?: "Invalid API key"))
            }
            is Failure.NetworkException,
            is Failure.ServerException,
            is Failure.NotFoundException,
            is Failure.UnknownException -> {
                networkStatus.postValue(NetworkState.error(ex.message ?: "Unknown Error!!!"))
            }
        }
    }

    fun getGuestSessionID(): String? {
        viewModelScope.launch(handler) {
            var guestSessionId = repository.getGuestSession()
            val guestSessionExpiration = repository.getGuestSessionExpiration()
//            val date = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(guestSessionExpiration ?: "")
            if (guestSessionId.isNullOrEmpty()) {
                repository.getGuestSessionId().let {
                    when (it) {
                        is Result.Success -> {
                            if (it.data != null) {
                                repository.setGuestSession(it.data.guest_session_id)
                                repository.setGuestSessionExpiration(it.data.expires_at)
                            }
                        }
                        is Result.Error -> {
                            networkStatus.postValue(NetworkState.error("Failed to get session id"))
                        }
                    }
                }
            }
        }
        return repository.getGuestSession()
    }
}