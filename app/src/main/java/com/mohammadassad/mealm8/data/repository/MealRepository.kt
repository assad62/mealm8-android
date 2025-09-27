package com.mohammadassad.mealm8.data.repository

import com.mohammadassad.mealm8.data.api.Meal
import com.mohammadassad.mealm8.data.api.TheMealDbApiService
import com.mohammadassad.mealm8.data.api.TheMealDbResponse

class MealRepository(
    private val theMealDbApiService: TheMealDbApiService
) {
    
    suspend fun getRandomMeal(): Result<TheMealDbResponse<List<Meal>>> {
        return theMealDbApiService.getRandomMeal()
    }
    
    suspend fun getMealById(id: String): Result<TheMealDbResponse<List<Meal>>> {
        return theMealDbApiService.getMealById(id)
    }
    
    suspend fun searchMealsByName(query: String): Result<TheMealDbResponse<List<Meal>>> {
        return theMealDbApiService.searchMealsByName(query)
    }
    
    suspend fun searchMealsByFirstLetter(letter: String): Result<TheMealDbResponse<List<Meal>>> {
        return theMealDbApiService.searchMealsByFirstLetter(letter)
    }
    
    suspend fun getCategories(): Result<TheMealDbResponse<List<com.mohammadassad.mealm8.data.api.Category>>> {
        return theMealDbApiService.getCategories()
    }
    
    suspend fun filterMealsByCategory(category: String): Result<TheMealDbResponse<List<com.mohammadassad.mealm8.data.api.MealSummary>>> {
        return theMealDbApiService.filterMealsByCategory(category)
    }
    
    suspend fun filterMealsByArea(area: String): Result<TheMealDbResponse<List<com.mohammadassad.mealm8.data.api.MealSummary>>> {
        return theMealDbApiService.filterMealsByArea(area)
    }
    
    suspend fun filterMealsByIngredient(ingredient: String): Result<TheMealDbResponse<List<com.mohammadassad.mealm8.data.api.MealSummary>>> {
        return theMealDbApiService.filterMealsByIngredient(ingredient)
    }
}


