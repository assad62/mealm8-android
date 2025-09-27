package com.mohammadassad.mealm8.features.categories.domain.repository

import com.mohammadassad.mealm8.features.categories.domain.model.Category

/**
 * Repository interface for categories
 * This defines the contract for data access in the domain layer
 */
interface CategoriesRepository {
    suspend fun getCategories(): Result<List<Category>>
}
