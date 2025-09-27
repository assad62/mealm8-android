package com.mohammadassad.mealm8.features.search.domain.model

/**
 * Enum representing the type of search to perform
 */
enum class SearchType {
    MEALS,
    INGREDIENTS
}

/**
 * Extension functions for SearchType
 */
fun SearchType.getSearchPlaceholder(): String {
    return when (this) {
        SearchType.MEALS -> "Search meals..."
        SearchType.INGREDIENTS -> "Search ingredients..."
    }
}

fun SearchType.getSearchTitle(): String {
    return when (this) {
        SearchType.MEALS -> "Search Meals"
        SearchType.INGREDIENTS -> "Search Ingredients"
    }
}
