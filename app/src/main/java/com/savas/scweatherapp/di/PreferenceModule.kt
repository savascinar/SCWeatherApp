package com.savas.scweatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.app.CityPreference
import com.savas.scweatherapp.preference.CityPreferenceSerializer
import com.savas.scweatherapp.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    @Singleton
    fun provideCityPreferenceDataStore(@ApplicationContext context: Context): DataStore<CityPreference> {

        return DataStoreFactory.create(
            serializer = CityPreferenceSerializer
        ) {
            context.filesDir.resolve("city_preference.pb")
        }

    }

    @Provides
    @Singleton
    fun providePreferenceManager(dataStore: DataStore<CityPreference>): PreferenceManager {
        return PreferenceManager(dataStore)
    }
}