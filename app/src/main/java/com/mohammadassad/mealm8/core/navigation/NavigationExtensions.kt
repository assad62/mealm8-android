package com.mohammadassad.mealm8.core.navigation

import androidx.navigation.NavController

// Extension functions for type-safe navigation
fun NavController.navigateToSplash() {
    navigate(Destination.Splash.route) {
        // No additional options needed
    }
}

fun NavController.navigateToMain() {
    navigate(Destination.Main.route) {
        // No additional options needed
    }
}


// Navigation with pop-up behavior
fun NavController.navigateAndReplace(destination: Destination) {
    navigate(destination.route) {
        popUpTo(0) { inclusive = true }
    }
}

fun NavController.navigateAndPopUpTo(
    destination: Destination,
    popUpToRoute: String,
    inclusive: Boolean = false
) {
    navigate(destination.route) {
        popUpTo(popUpToRoute) { this.inclusive = inclusive }
    }
}

// Navigation with options
fun NavController.navigateWithOptions(
    destination: Destination,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    navigate(destination.route) {
        popUpToRoute?.let { route ->
            popUpTo(route) { this.inclusive = inclusive }
        }
    }
}
