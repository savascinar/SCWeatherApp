package com.savas.scweatherapp.search

data class SearchResultData(
    val name: String,
    val temp: Double,
    val iconUrl: String,
    val lat: Double,
    val lon: Double
)