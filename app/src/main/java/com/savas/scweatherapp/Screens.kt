package com.savas.scweatherapp

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Search : Screens("search")
}