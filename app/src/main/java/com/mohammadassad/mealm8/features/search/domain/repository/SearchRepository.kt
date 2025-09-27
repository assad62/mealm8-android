package com.mohammadassad.mealm8.features.search.domain.repository

import com.mohammadassad.mealm8.features.search.domain.model.SearchResult
import com.mohammadassad.mealm8.features.search.domain.model.SearchType

/**
 * Repository interface for search functionality
 */
interface SearchRepository {
    suspend fun searchMeals(query: String): Result<List<SearchResult>>
    suspend fun searchCuisines(query: String): Result<List<SearchResult>>
    suspend fun searchIngredients(query: String): Result<List<SearchResult>>
    
    suspend fun search(query: String, type: SearchType): Result<List<SearchResult>>
}
