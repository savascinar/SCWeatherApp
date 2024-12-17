package com.savas.scweatherapp.network.model

import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("name")
    val name: String = ""

    @SerializedName("lat")
    val lat: Double = Double.NaN

    @SerializedName("lon")
    val lon: Double = Double.NaN
}