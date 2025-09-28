package com.mohammadassad.mealm8.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohammadassad.mealm8.core.presentation.MainScreen
import com.mohammadassad.mealm8.features.recipes.presentation.CategoryDetailScreen
import com.mohammadassad.mealm8.features.recipes.presentation.RecipeDetailScreen
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
                    navController.navigate(Destination.Main.createRoute(0)) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        
        composable(
            route = Destination.Main.route,
            arguments = listOf(
                androidx.navigation.navArgument("tabIndex") { 
                    type = androidx.navigation.NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val tabIndex = backStackEntry.arguments?.getInt("tabIndex") ?: 0
            MainScreen(
                navController = navController,
                initialTabIndex = tabIndex
            )
        }
        
        // Detail Screens
        composable(
            route = Destination.CategoryDetail.route,
            arguments = listOf(
                androidx.navigation.navArgument("type") { type = androidx.navigation.NavType.StringType },
                androidx.navigation.navArgument("name") { type = androidx.navigation.NavType.StringType },
                androidx.navigation.navArgument("fromTab") { type = androidx.navigation.NavType.IntType }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val fromTab = backStackEntry.arguments?.getInt("fromTab") ?: 0
            
            CategoryDetailScreen(
                type = type,
                name = name,
                onBackClick = { navController.navigateBackToTab(fromTab) }, // Return to the tab we came from
                onMealClick = { mealId ->
                    navController.navigateToRecipeDetail(mealId)
                }
            )
        }
        
        composable(
            route = Destination.RecipeDetail.route,
            arguments = listOf(
                androidx.navigation.navArgument("mealId") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            
            RecipeDetailScreen(
                mealId = mealId,
                onBackClick = { navController.navigateBack() } // This will go back to CategoryDetail, which will handle the tab
            )
        }
    }
}
