package com.savas.scweatherapp.util

class FormatHelper {

    companion object {
        fun formatWeatherIconUrl(url: String): String {
            return if (url.startsWith("//")) {
                "https:$url"
            } else {
                url
            }
        }
    }
}