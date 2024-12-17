package com.savas.scweatherapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savas.scweatherapp.Resource
import com.savas.scweatherapp.repository.WeatherRepository
import com.savas.scweatherapp.preference.PreferenceManager
import com.savas.scweatherapp.util.ErrorHelper
import com.savas.scweatherapp.util.FormatHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val preferenceManager: PreferenceManager

) : ViewModel() {

    private val homeUIStateInternal =
        MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val homeUIState: StateFlow<HomeUIState> = homeUIStateInternal

    suspend fun getCurrentWeather() {
        viewModelScope.launch {
            if (preferenceManager.hasCityPreference()) {
                preferenceManager.cityPreferenceFlow.collect { preference ->
                    val response = weatherRepository.fetchCurrentWeather(
                        preference.latitude,
                        preference.longitude
                    )

                    if (response is Resource.Success) {
                        val currentWeatherEntity = response.data

                        homeUIStateInternal.value =
                            HomeUIState.WeatherData(
                                currentWeatherEntity.locationEntity.locationName,
                                currentWeatherEntity.temp,
                                FormatHelper.formatWeatherIconUrl(currentWeatherEntity.iconUrl),
                                currentWeatherEntity.feelsTemp,
                                currentWeatherEntity.humidity,
                                currentWeatherEntity.uv
                            )


                    } else if (response is Resource.Error) {
                        homeUIStateInternal.value =
                            HomeUIState.Error(ErrorHelper.getErrorType(response.exception))
                    }

                }
            } else {
                homeUIStateInternal.value = HomeUIState.PromptSearch
            }
        }
    }

}