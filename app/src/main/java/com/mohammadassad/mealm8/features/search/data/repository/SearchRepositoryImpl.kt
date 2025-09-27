package com.mohammadassad.mealm8.features.search.data.repository

import com.mohammadassad.mealm8.core.data.api.TheMealDbApiService
import com.mohammadassad.mealm8.features.search.domain.model.SearchResult
import com.mohammadassad.mealm8.features.search.domain.model.SearchType
import com.mohammadassad.mealm8.features.search.domain.repository.SearchRepository

/**
 * Repository implementation for search functionality
 */
class SearchRepositoryImpl(
    private val theMealDbApiService: TheMealDbApiService
) : SearchRepository {

    override suspend fun searchMeals(query: String): Result<List<SearchResult>> {
        return theMealDbApiService.searchMealsByName(query)
            .map { response ->
                response.meals?.map { meal ->
                    SearchResult.fromMeal(meal)
                } ?: emptyList()
            }
    }



    override suspend fun searchIngredients(query: String): Result<List<SearchResult>> {
        return theMealDbApiService.getIngredientList()
            .map { response ->
                response.meals?.filter { ingredient ->
                    ingredient.strIngredient?.contains(query, ignoreCase = true) == true
                }?.map { ingredient ->
                    SearchResult.fromIngredient(ingredient)
                } ?: emptyList()
            }
    }

    override suspend fun search(query: String, type: SearchType): Result<List<SearchResult>> {
        return when (type) {
            SearchType.MEALS -> searchMeals(query)
            SearchType.INGREDIENTS -> searchIngredients(query)
        }
    }
}
