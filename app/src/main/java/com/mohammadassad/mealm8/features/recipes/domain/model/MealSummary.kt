package com.mohammadassad.mealm8.features.recipes.domain.model

/**
 * Domain model for Meal Summary
 * This represents a simplified meal for list views
 */
data class MealSummary(
    val id: String,
    val name: String,
    val imageUrl: String?
) {
    companion object {
        fun fromApiModel(apiModel: com.mohammadassad.mealm8.core.data.api.MealSummary): MealSummary {
            return MealSummary(
                id = apiModel.idMeal ?: "",
                name = apiModel.strMeal ?: "",
                imageUrl = apiModel.strMealThumb
            )
        }
    }
}
