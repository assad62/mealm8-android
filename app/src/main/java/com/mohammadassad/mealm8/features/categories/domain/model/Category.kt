package com.mohammadassad.mealm8.features.categories.domain.model

/**
 * Domain model for Category
 * This represents the business entity for a meal category
 */
data class Category(
    val name: String,
    val imageUrl: String
) {
    companion object {
        fun fromApiModel(apiModel: com.mohammadassad.mealm8.core.data.api.CategoryItem): Category {
            val categoryName = apiModel.strCategory ?: "Unknown"
            return Category(
                name = categoryName,
                imageUrl = "https://www.themealdb.com/images/category/${categoryName.lowercase()}.png"
            )
        }
    }
}
