package com.mohammadassad.mealm8.core.navigation

import androidx.navigation.NavController

// Extension functions for type-safe navigation
fun NavController.navigateToSplash() {
    navigate(Destination.Splash.route) {
        // No additional options needed
    }
}

fun NavController.navigateToMain(tabIndex: Int = 0) {
    navigate(Destination.Main.createRoute(tabIndex)) {
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

// Detail Screen Navigation
fun NavController.navigateToCategoryDetail(type: String, name: String, fromTab: Int) {
    navigate(Destination.CategoryDetail.createRoute(type, name, fromTab))
}

fun NavController.navigateToRecipeDetail(mealId: String) {
    navigate(Destination.RecipeDetail.createRoute(mealId))
}

// Back Navigation
fun NavController.navigateBack() {
    if (!popBackStack()) {
        // If we can't pop back, navigate to main
        navigateToMain()
    }
}

// Back Navigation with tab preservation
fun NavController.navigateBackToTab(tabIndex: Int) {
    if (!popBackStack()) {
        // If we can't pop back, navigate to main with specific tab
        navigateToMain(tabIndex)
    }
}
