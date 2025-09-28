package com.mohammadassad.mealm8.core.navigation

sealed class Destination(val route: String) {
    object Splash : Destination("splash")
    object Main : Destination("main/{tabIndex}") {
        fun createRoute(tabIndex: Int = 0) = "main/$tabIndex"
    }
    
    // Bottom Navigation Tabs
    object Home : Destination("home")
    object Categories : Destination("categories")
    object Search : Destination("search")
    object Favourites : Destination("favourites")
    
    // Detail Screens
    object CategoryDetail : Destination("category_detail/{type}/{name}/{fromTab}") {
        fun createRoute(type: String, name: String, fromTab: Int) = "category_detail/$type/$name/$fromTab"
    }
    object RecipeDetail : Destination("recipe_detail/{mealId}") {
        fun createRoute(mealId: String) = "recipe_detail/$mealId"
    }
}
