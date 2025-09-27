package com.mohammadassad.mealm8.features.browse.domain.repository

import com.mohammadassad.mealm8.features.browse.domain.model.BrowseItem

/**
 * Repository interface for browse functionality
 * Handles all three types of browse data: Categories, Areas, Ingredients
 */
interface BrowseRepository {
    suspend fun getCategories(): Result<List<BrowseItem>>
    suspend fun getAreas(): Result<List<BrowseItem>>
    suspend fun getIngredients(): Result<List<BrowseItem>>
    suspend fun getAllBrowseData(): Result<Map<com.mohammadassad.mealm8.features.browse.domain.model.BrowseType, List<BrowseItem>>>
}
