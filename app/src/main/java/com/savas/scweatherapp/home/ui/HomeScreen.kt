package com.savas.scweatherapp.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.savas.scweatherapp.home.HomeUIState
import com.savas.scweatherapp.home.HomeViewModel
import com.savas.scweatherapp.ui.common.ErrorUI
import com.savas.scweatherapp.ui.common.ProgressBar
import com.savas.scweatherapp.util.ErrorHelper

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by homeViewModel.homeUIState.collectAsState()


    LaunchedEffect(Unit) {
        homeViewModel.getCurrentWeather()
    }

    when (uiState) {

        is HomeUIState.Loading -> {
            ProgressBar()
        }

        is HomeUIState.PromptSearch -> {
            PromptSearchMessage()
        }

        is HomeUIState.Error -> {
            val context = LocalContext.current
            ErrorUI(ErrorHelper.getErrorMessage(context, (uiState as HomeUIState.Error).errorType))
        }

        is HomeUIState.WeatherData -> {
            val weatherData = (uiState as HomeUIState.WeatherData)

            WeatherOverviewScreen(
                modifier
                    .fillMaxSize()
                    .padding(top = 20.dp), weatherData)

        }

    }
}