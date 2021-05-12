package com.thesis.week6.Movie.rest

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", "b4ae39a7e05c4636498b82ddcb9656d4")
            .build()

        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
