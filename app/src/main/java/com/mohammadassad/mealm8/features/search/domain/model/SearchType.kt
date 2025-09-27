package com.mohammadassad.mealm8.features.search.domain.model

/**
 * Enum representing the type of search to perform
 */
enum class SearchType {
    MEALS,
    CUISINES,
    INGREDIENTS
}

/**
 * Extension functions for SearchType
 */
fun SearchType.getSearchPlaceholder(): String {
    return when (this) {
        SearchType.MEALS -> "Search meals..."
        SearchType.CUISINES -> "Search cuisines..."
        SearchType.INGREDIENTS -> "Search ingredients..."
    }
}

fun SearchType.getSearchTitle(): String {
    return when (this) {
        SearchType.MEALS -> "Search Meals"
        SearchType.CUISINES -> "Search Cuisines"
        SearchType.INGREDIENTS -> "Search Ingredients"
    }
}
