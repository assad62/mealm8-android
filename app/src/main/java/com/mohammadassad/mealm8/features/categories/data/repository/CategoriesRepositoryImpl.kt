package com.mohammadassad.mealm8.features.categories.data.repository

import com.mohammadassad.mealm8.core.data.api.TheMealDbResponse
import com.mohammadassad.mealm8.core.data.api.CategoryItem
import com.mohammadassad.mealm8.features.categories.domain.model.Category
import com.mohammadassad.mealm8.features.categories.domain.repository.CategoriesRepository

/**
 * Repository implementation for categories
 * This handles the data access logic for categories
 */
class CategoriesRepositoryImpl(
    private val theMealDbApiService: com.mohammadassad.mealm8.core.data.api.TheMealDbApiService
) : CategoriesRepository {
    
    override suspend fun getCategories(): Result<List<Category>> {
        return theMealDbApiService.getCategoryList()
            .map { response ->
                response.meals?.map { categoryItem ->
                    Category.fromApiModel(categoryItem)
                } ?: emptyList()
            }
    }
}
