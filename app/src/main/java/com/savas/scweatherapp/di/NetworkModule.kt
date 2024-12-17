package com.savas.scweatherapp.di

import com.savas.scweatherapp.network.SCWeatherAppKeyInterceptor
import com.savas.scweatherapp.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = " https://api.weatherapi.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val apiKey = "bf1ba7903c6c41d7abe160038241412"

    @Provides
    @Singleton
    fun provideHttpClient(
    ): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(SCWeatherAppKeyInterceptor(apiKey))


        return builder.build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(httpClient: OkHttpClient): WeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)
}