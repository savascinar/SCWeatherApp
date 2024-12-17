package com.savas.scweatherapp.preference

import androidx.datastore.core.DataStore
import com.example.app.CityPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.IOException


class PreferenceManager( private val cityPreferenceDataStore: DataStore<CityPreference>) {

    val cityPreferenceFlow: Flow<CityPreference> = cityPreferenceDataStore.data

    suspend fun saveCityPreference(cityName: String, lat: Double, lon: Double): Boolean {
        return try {
            cityPreferenceDataStore.updateData { currentPreferences ->
                currentPreferences.toBuilder()
                    .setName(cityName)
                    .setLatitude(lat)
                    .setLongitude(lon)
                    .build()
            }
            // Update successful
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun hasCityPreference(): Boolean {
        return try {
            val cityPreference = cityPreferenceFlow.first()
            cityPreference != CityPreference.getDefaultInstance()
        } catch (ex: Exception) {
            false
        }
    }

}