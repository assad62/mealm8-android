package com.mohammadassad.mealm8.navigation

sealed class Destination(val route: String) {
    object Splash : Destination("splash")
    object Main : Destination("main")
    
    // Bottom Navigation Tabs
    object Home : Destination("home")
    object Categories : Destination("categories")
    object Search : Destination("search")
    object Favourites : Destination("favourites")
}
