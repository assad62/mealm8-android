package com.mohammadassad.mealm8.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohammadassad.mealm8.ui.screens.HomeScreen
import com.mohammadassad.mealm8.ui.screens.SettingsScreen
import com.mohammadassad.mealm8.ui.screens.SplashScreen

@Composable
fun MealM8Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route
    ) {
        composable(Destination.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigateAndReplace(Destination.Home)
                }
            )
        }
        
        composable(Destination.Home.route) {
            HomeScreen(
                onNavigateToSettings = {
                    navController.navigateToSettings()
                }
            )
        }
        
        composable(Destination.Settings.route) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
