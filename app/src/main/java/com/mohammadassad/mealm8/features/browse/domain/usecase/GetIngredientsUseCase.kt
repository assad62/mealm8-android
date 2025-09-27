package com.mohammadassad.mealm8.features.browse.domain.usecase

import com.mohammadassad.mealm8.features.browse.domain.model.BrowseItem
import com.mohammadassad.mealm8.features.browse.domain.repository.BrowseRepository

/**
 * Use case for getting ingredients
 */
class GetIngredientsUseCase(
    private val browseRepository: BrowseRepository
) {
    suspend operator fun invoke(): Result<List<BrowseItem>> {
        return browseRepository.getIngredients()
    }
}
