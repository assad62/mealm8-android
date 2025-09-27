package com.mohammadassad.mealm8.features.search.domain.usecase

import com.mohammadassad.mealm8.features.search.domain.model.SearchResult
import com.mohammadassad.mealm8.features.search.domain.model.SearchType
import com.mohammadassad.mealm8.features.search.domain.repository.SearchRepository

/**
 * Use case for performing search operations
 */
class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String, type: SearchType): Result<List<SearchResult>> {
        return if (query.isBlank()) {
            Result.success(emptyList())
        } else {
            searchRepository.search(query.trim(), type)
        }
    }
}
