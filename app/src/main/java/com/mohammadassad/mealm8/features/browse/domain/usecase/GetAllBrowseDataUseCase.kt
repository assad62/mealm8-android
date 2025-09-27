package com.mohammadassad.mealm8.features.browse.domain.usecase

import com.mohammadassad.mealm8.features.browse.domain.model.BrowseItem
import com.mohammadassad.mealm8.features.browse.domain.model.BrowseType
import com.mohammadassad.mealm8.features.browse.domain.repository.BrowseRepository

/**
 * Use case for getting all browse data at once
 * This is more efficient than making separate calls
 */
class GetAllBrowseDataUseCase(
    private val browseRepository: BrowseRepository
) {
    suspend operator fun invoke(): Result<Map<BrowseType, List<BrowseItem>>> {
        return browseRepository.getAllBrowseData()
    }
}
