package com.savas.scweatherapp.util

import java.util.Locale

class FormatHelper {

    companion object {
        fun formatWeatherIconUrl(url: String): String {
            return if (url.startsWith("//")) {
                "https:$url"
            } else {
                url
            }
        }

        fun formatUV(value: Double): String {
            return if (value == 0.0) "0" else String.format(Locale.US, "%.1f", value)
        }
    }
}