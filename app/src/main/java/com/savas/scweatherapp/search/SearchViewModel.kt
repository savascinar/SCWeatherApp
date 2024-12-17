package com.savas.scweatherapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savas.scweatherapp.ErrorType
import com.savas.scweatherapp.Resource
import com.savas.scweatherapp.repository.WeatherRepository
import com.savas.scweatherapp.preference.PreferenceManager
import com.savas.scweatherapp.repository.LocationEntity
import com.savas.scweatherapp.util.ErrorHelper
import com.savas.scweatherapp.util.FormatHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val preferenceManager: PreferenceManager

) : ViewModel() {

    private val searchUIStateInternal =
        MutableStateFlow<SearchUIState>(SearchUIState.Loading)
    val searchUIState: StateFlow<SearchUIState> = searchUIStateInternal

    private val navigateInternal =
        MutableSharedFlow<Unit>()
    val navigate: SharedFlow<Unit> = navigateInternal

    suspend fun searchCity(query: String) {
        viewModelScope.launch {
            val response = weatherRepository.fetchLocations(query)

            if (response is Resource.Success) {
                val resulData = getCurrentWeathers(response.data)
                searchUIStateInternal.value = SearchUIState.Data(resulData)
            } else if (response is Resource.Error) {
                searchUIStateInternal.value =
                    SearchUIState.Error(ErrorHelper.getErrorType(response.exception))
            }
        }
    }

    // Send http request for each location parallel
    private suspend fun getCurrentWeathers(locations: List<LocationEntity>): List<SearchResultData> {
        return viewModelScope.async {
            locations.map { location ->
                async {
                    when (val weatherDetailEntity =
                        weatherRepository.fetchCurrentWeather(location.lat, location.lon)) {
                        is Resource.Error -> {
                            null
                        }

                        is Resource.Success -> {
                            val currentWeatherEntity = weatherDetailEntity.data

                            SearchResultData(
                                location.locationName,
                                currentWeatherEntity.temp,
                                FormatHelper.formatWeatherIconUrl(
                                    currentWeatherEntity.iconUrl
                                ),
                                location.lat,
                                location.lon
                            )

                        }
                    }
                }

            }.awaitAll().filterNotNull()
        }.await()
    }

    fun selectCity(name: String, lat: Double, lon: Double) {
        viewModelScope.launch {
            val isSaved = preferenceManager.saveCityPreference(name, lat, lon)
            if (isSaved) {
                //searchUIStateInternal.value = SearchUIState.Navigate
                viewModelScope.launch {
                    navigateInternal.emit(Unit)
                }
            } else {
                searchUIStateInternal.value = SearchUIState.Error(ErrorType.UnknownError)
            }
        }
    }
}