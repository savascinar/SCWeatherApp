package com.savas.scweatherapp.repository

import com.savas.scweatherapp.Resource
import com.savas.scweatherapp.network.WeatherApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,

    ) {
    suspend fun fetchLocations(keyword: String): Resource<List<LocationEntity>> {
        try {
            val currentWeatherResponse =
                weatherApi.getLocations(keyword)

            if (currentWeatherResponse.isSuccessful) {
                val body = currentWeatherResponse.body()
                if (body != null) {

                    val locationEntityList = body.map { LocationEntity(it.name, it.lat, it.lon) }

                    return Resource.Success(locationEntityList)
                } else {
                    return Resource.Error(IllegalStateException("Search Response null"))
                }
            } else {
                return Resource.Error(HttpException(currentWeatherResponse))
            }
        } catch (e: IOException) {
            return Resource.Error(e)
        } catch (e: Exception) {
            return Resource.Error(e)
        }
    }

    suspend fun fetchCurrentWeather(lat: Double, lon: Double): Resource<CurrentWeatherEntity> {

        try {
            val latLong = "$lat,$lon"
            val currentWeatherResponse =
                weatherApi.getCurrentWeather(latLong)

            if (currentWeatherResponse.isSuccessful) {
                val body = currentWeatherResponse.body()
                if (body != null) {

                    val currentWeather = body.currentWeather
                    val weatherConditionResponse = body.currentWeather.weatherCondition

                    val locationEntity =
                        LocationEntity(body.location.name, body.location.lat, body.location.lon)

                    val currentWeatherEntity = CurrentWeatherEntity(
                        currentWeather.tempC,
                        currentWeather.feelslikeC,
                        currentWeather.uv,
                        currentWeather.humidity,
                        weatherConditionResponse.icon,
                        locationEntity
                    )

                    return Resource.Success(currentWeatherEntity)
                } else {
                    return Resource.Error(IllegalStateException("CurrentWeather response is null"))
                }
            } else {
                return Resource.Error(HttpException(currentWeatherResponse))
            }
        } catch (e: IOException) {
            return Resource.Error(e)
        } catch (e: Exception) {
            return Resource.Error(e)
        }

    }
}