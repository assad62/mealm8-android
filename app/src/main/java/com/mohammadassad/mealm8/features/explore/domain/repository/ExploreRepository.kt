package com.mohammadassad.mealm8.features.explore.domain.repository

import com.mohammadassad.mealm8.features.explore.domain.model.ExploreItem

/**
 * Repository interface for explore functionality
 * Handles all three types of explore data: Categories, Areas, Ingredients
 */
interface ExploreRepository {
    suspend fun getCategories(): Result<List<ExploreItem>>
    suspend fun getAreas(): Result<List<ExploreItem>>
    suspend fun getIngredients(): Result<List<ExploreItem>>
    suspend fun getAllExploreData(): Result<Map<com.mohammadassad.mealm8.features.explore.domain.model.ExploreType, List<ExploreItem>>>
}
