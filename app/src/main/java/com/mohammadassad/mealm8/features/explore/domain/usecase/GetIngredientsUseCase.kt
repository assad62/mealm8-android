package com.mohammadassad.mealm8.features.explore.domain.usecase

import com.mohammadassad.mealm8.features.explore.domain.model.ExploreItem
import com.mohammadassad.mealm8.features.explore.domain.repository.ExploreRepository

/**
 * Use case for getting ingredients
 */
class GetIngredientsUseCase(
    private val exploreRepository: ExploreRepository
) {
    suspend operator fun invoke(): Result<List<ExploreItem>> {
        return exploreRepository.getIngredients()
    }
}
