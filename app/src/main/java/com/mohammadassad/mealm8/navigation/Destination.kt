package com.mohammadassad.mealm8.navigation

sealed class Destination(val route: String) {
    object Splash : Destination("splash")
    object Home : Destination("home")
    object Settings : Destination("settings")
}
