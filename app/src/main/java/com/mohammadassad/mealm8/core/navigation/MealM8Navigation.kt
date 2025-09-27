package com.mohammadassad.mealm8.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohammadassad.mealm8.core.presentation.MainScreen
import com.mohammadassad.mealm8.features.splash.presentation.SplashScreen

@Composable
fun MealM8Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route
    ) {
        composable(Destination.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigateAndReplace(Destination.Main)
                }
            )
        }
        
        composable(Destination.Main.route) {
            MainScreen(navController = navController)
        }
    }
}
