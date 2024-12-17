package com.savas.scweatherapp.network

import com.savas.scweatherapp.network.model.CurrentWeatherResponse
import com.savas.scweatherapp.network.model.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("search.json")
    suspend fun getLocations(
        @Query("q") name: String
    ): Response<List<Location>>

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") latLong: String
    ): Response<CurrentWeatherResponse>
}