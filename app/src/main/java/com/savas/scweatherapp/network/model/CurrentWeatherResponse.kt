package com.savas.scweatherapp.network.model

import com.google.gson.annotations.SerializedName

class CurrentWeatherResponse {
    @SerializedName("current")
    val currentWeather: CurrentWeather = CurrentWeather()

    @SerializedName("location")
    val location: Location = Location()


}