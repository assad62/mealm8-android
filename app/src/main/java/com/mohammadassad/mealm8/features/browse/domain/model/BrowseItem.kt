package com.mohammadassad.mealm8.features.browse.domain.model

/**
 * Unified domain model for all browse items (Categories, Areas, Ingredients)
 */
data class BrowseItem(
    val id: String,
    val name: String,
    val type: BrowseType,
    val imageUrl: String? = null,
    val description: String? = null
) {
    companion object {
        fun fromCategory(apiModel: com.mohammadassad.mealm8.core.data.api.CategoryItem): BrowseItem {
            val categoryName = apiModel.strCategory ?: "Unknown"
            return BrowseItem(
                id = categoryName.lowercase(),
                name = categoryName,
                type = BrowseType.CATEGORY,
                imageUrl = "https://www.themealdb.com/images/category/${categoryName.lowercase()}.png"
            )
        }
        
        fun fromArea(apiModel: com.mohammadassad.mealm8.core.data.api.AreaItem): BrowseItem {
            val areaName = apiModel.strArea ?: "Unknown"
            return BrowseItem(
                id = areaName.lowercase(),
                name = areaName,
                type = BrowseType.AREA,
                imageUrl = getAreaImageUrl(areaName)
            )
        }
        
        fun fromIngredient(apiModel: com.mohammadassad.mealm8.core.data.api.IngredientItem): BrowseItem {
            val ingredientName = apiModel.strIngredient ?: "Unknown"
            return BrowseItem(
                id = ingredientName.lowercase(),
                name = ingredientName,
                type = BrowseType.INGREDIENT,
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
 * Enum representing the type of browse item
 */
enum class BrowseType {
    CATEGORY,
    AREA,
    INGREDIENT
}
