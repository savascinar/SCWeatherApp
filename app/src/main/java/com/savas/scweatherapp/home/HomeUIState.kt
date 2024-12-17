package com.savas.scweatherapp.home

import com.savas.scweatherapp.ErrorType

sealed class HomeUIState {
    data class WeatherData(
        val name: String,
        val temp: Double,
        val iconUrl: String,
        val feelLikeTemp: Double,
        val humidity: Int,
        val uv: Double
    ) : HomeUIState()

    data object PromptSearch : HomeUIState()
    data class Error(val errorType: ErrorType) : HomeUIState()
    data object Loading : HomeUIState()
}