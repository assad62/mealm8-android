package com.mohammadassad.mealm8.features.categories.domain.usecase

import com.mohammadassad.mealm8.features.categories.domain.model.Category
import com.mohammadassad.mealm8.features.categories.domain.repository.CategoriesRepository

/**
 * Use case for getting categories
 * This encapsulates the business logic for fetching categories
 */
class GetCategoriesUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(): Result<List<Category>> {
        return categoriesRepository.getCategories()
    }
}
