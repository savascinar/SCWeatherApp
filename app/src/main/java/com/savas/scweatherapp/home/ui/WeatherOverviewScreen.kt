package com.savas.scweatherapp.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.savas.scweatherapp.home.HomeUIState

@Composable
fun WeatherOverviewScreen(modifier: Modifier, weatherData: HomeUIState.WeatherData) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WeatherInfo(
            modifier = Modifier,
            name = weatherData.name,
            iconUrl = weatherData.iconUrl,
            temp = weatherData.temp
        )

        WeatherDetailCard(weatherData.humidity, weatherData.uv, weatherData.feelLikeTemp)
    }
}