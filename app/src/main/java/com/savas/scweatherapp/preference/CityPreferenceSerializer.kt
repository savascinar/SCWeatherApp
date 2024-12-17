package com.savas.scweatherapp.preference

import androidx.datastore.core.Serializer
import com.example.app.CityPreference
import java.io.InputStream
import java.io.OutputStream

object CityPreferenceSerializer : Serializer<CityPreference> {
    override val defaultValue: CityPreference = CityPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): CityPreference {
        return CityPreference.parseFrom(input)
    }

    override suspend fun writeTo(t: CityPreference, output: OutputStream) {
        t.writeTo(output)
    }
}