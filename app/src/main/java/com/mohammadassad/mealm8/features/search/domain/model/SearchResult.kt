package com.mohammadassad.mealm8.features.search.domain.model

/**
 * Unified search result model for all search types
 */
data class SearchResult(
    val id: String,
    val name: String,
    val type: SearchType,
    val imageUrl: String? = null,
    val description: String? = null,
    val category: String? = null,
    val area: String? = null
) {
    companion object {
        fun fromMeal(meal: com.mohammadassad.mealm8.core.data.api.Meal): SearchResult {
            return SearchResult(
                id = meal.idMeal ?: "",
                name = meal.strMeal ?: "",
                type = SearchType.MEALS,
                imageUrl = meal.strMealThumb,
                description = meal.strInstructions?.take(100) + "...",
                category = meal.strCategory,
                area = meal.strArea
            )
        }
        
        fun fromMealSummary(mealSummary: com.mohammadassad.mealm8.core.data.api.MealSummary): SearchResult {
            return SearchResult(
                id = mealSummary.idMeal ?: "",
                name = mealSummary.strMeal ?: "",
                type = SearchType.MEALS,
                imageUrl = mealSummary.strMealThumb
            )
        }
        
        
        
        fun fromIngredient(ingredient: com.mohammadassad.mealm8.core.data.api.IngredientItem): SearchResult {
            return SearchResult(
                id = ingredient.strIngredient?.lowercase() ?: "",
                name = ingredient.strIngredient ?: "",
                type = SearchType.INGREDIENTS,
                imageUrl = "https://www.themealdb.com/images/ingredients/${ingredient.strIngredient?.lowercase()}.png"
            )
        }
    }
}
