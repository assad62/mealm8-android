package com.mohammadassad.mealm8.features.explore.domain.usecase

import com.mohammadassad.mealm8.features.explore.domain.model.ExploreItem
import com.mohammadassad.mealm8.features.explore.domain.model.ExploreType
import com.mohammadassad.mealm8.features.explore.domain.repository.ExploreRepository

/**
 * Use case for getting all explore data at once
 * This is more efficient than making separate calls
 */
class GetAllExploreDataUseCase(
    private val exploreRepository: ExploreRepository
) {
    suspend operator fun invoke(): Result<Map<ExploreType, List<ExploreItem>>> {
        return exploreRepository.getAllExploreData()
    }
}
