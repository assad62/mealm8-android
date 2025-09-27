package com.mohammadassad.mealm8.features.explore.domain.model

/**
 * Unified domain model for all explore items (Categories, Areas, Ingredients)
 */
data class ExploreItem(
    val id: String,
    val name: String,
    val type: ExploreType,
    val imageUrl: String? = null,
    val description: String? = null
) {
    companion object {
        fun fromCategory(apiModel: com.mohammadassad.mealm8.core.data.api.CategoryItem): ExploreItem {
            val categoryName = apiModel.strCategory ?: "Unknown"
            return ExploreItem(
                id = categoryName.lowercase(),
                name = categoryName,
                type = ExploreType.CATEGORY,
                imageUrl = "https://www.themealdb.com/images/category/${categoryName.lowercase()}.png"
            )
        }
        
        fun fromArea(apiModel: com.mohammadassad.mealm8.core.data.api.AreaItem): ExploreItem {
            val areaName = apiModel.strArea ?: "Unknown"
            return ExploreItem(
                id = areaName.lowercase(),
                name = areaName,
                type = ExploreType.AREA,
                imageUrl = getAreaImageUrl(areaName)
            )
        }
        
        fun fromIngredient(apiModel: com.mohammadassad.mealm8.core.data.api.IngredientItem): ExploreItem {
            val ingredientName = apiModel.strIngredient ?: "Unknown"
            return ExploreItem(
                id = ingredientName.lowercase(),
                name = ingredientName,
                type = ExploreType.INGREDIENT,
                imageUrl = getIngredientImageUrl(ingredientName)
            )
        }
        
        private fun getAreaImageUrl(areaName: String): String {
            // Use country flag or cuisine-specific images
            return "https://www.themealdb.com/images/ingredients/${areaName.lowercase()}.png"
        }
        
        private fun getIngredientImageUrl(ingredientName: String): String {
            return "https://www.themealdb.com/images/ingredients/${ingredientName.lowercase()}.png"
        }
    }
}

/**
 * Enum representing the type of explore item
 */
enum class ExploreType {
    CATEGORY,
    AREA,
    INGREDIENT
}
