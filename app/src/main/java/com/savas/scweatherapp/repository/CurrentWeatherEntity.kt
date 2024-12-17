package com.savas.scweatherapp.repository

class CurrentWeatherEntity(
    val temp: Double,
    val feelsTemp: Double,
    val uv: Double,
    val humidity: Int,
    val iconUrl: String,
    val locationEntity: LocationEntity
)