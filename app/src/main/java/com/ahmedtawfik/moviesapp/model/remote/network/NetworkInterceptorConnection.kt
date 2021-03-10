package com.ahmedtawfik.moviesapp.model.remote.network

import android.content.Context
import com.ahmedtawfik.moviesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptorConnection(var context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        var orginalHttpURL = original.url

        var url = orginalHttpURL.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        var request = original
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)

    }
}