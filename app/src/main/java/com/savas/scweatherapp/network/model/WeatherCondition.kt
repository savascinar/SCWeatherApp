package com.savas.scweatherapp.network.model

import com.google.gson.annotations.SerializedName

class WeatherCondition {

    @SerializedName("text")
    val text: String = ""

    @SerializedName("icon")
    val icon: String = ""

    @SerializedName("code")
    val code: Int = Int.MIN_VALUE
}