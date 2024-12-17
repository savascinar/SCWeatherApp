package com.savas.scweatherapp.di

import okhttp3.Interceptor
import okhttp3.Response

class SCWeatherAppKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .url(
                originalRequest.url
                    .newBuilder()
                    .addQueryParameter("key", apiKey)
                    .build()
            )
            .build()

        return chain.proceed(newRequest)
    }
}