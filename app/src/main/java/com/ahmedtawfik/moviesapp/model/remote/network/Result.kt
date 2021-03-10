package com.ahmedtawfik.moviesapp.model.remote.network

import java.io.IOException

sealed class Result<out T> {
    data class Success<T>(val data: T?) : Result<T>()
    class Error(val exception: Failure.UnknownException) : Result<Nothing>()
}

sealed class Failure(open val msg: String?) :
    IOException(msg) {
    data class ServerException(
        override val msg: String?
    ) : Failure(msg)

    data class NetworkException(
        override val msg: String?
    ) : Failure(msg)

    open class UnknownException(
        override val msg: String?
    ) : Failure(msg)

    open class InvalidAPIKeyException(
        override val msg: String?
    ) : Failure(msg)

    open class NotFoundException(
        override val msg: String?
    ) : Failure(msg)
}