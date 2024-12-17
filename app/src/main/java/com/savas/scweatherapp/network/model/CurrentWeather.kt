package com.savas.scweatherapp.network.model

import com.google.gson.annotations.SerializedName

class CurrentWeather {

    @SerializedName("feelslike_c")
    val feelslikeC: Double = Double.NaN

    @SerializedName("feelslike_f")
    val feelslike_f: Double = Double.NaN

    @SerializedName("temp_c")
    val tempC: Double = Double.NaN

    @SerializedName("temp_f")
    val tempf: Double = Double.NaN

    @SerializedName("uv")
    val uv: Double = Double.NaN

    @SerializedName("humidity")
    val humidity: Int = Int.MIN_VALUE

    @SerializedName("condition")
    val weatherCondition: WeatherCondition = WeatherCondition()
}