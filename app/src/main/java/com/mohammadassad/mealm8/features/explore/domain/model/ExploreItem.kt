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
        
        private fun getAreaImageUrl(areaName: String): String? {
            // Return null for areas since we'll use emoji icons instead
            return null
        }
        
        fun getAreaEmoji(areaName: String): String {
            return when (areaName.lowercase()) {
                "american" -> "🇺🇸"
                "british" -> "🇬🇧"
                "canadian" -> "🇨🇦"
                "chinese" -> "🇨🇳"
                "croatian" -> "🇭🇷"
                "dutch" -> "🇳🇱"
                "egyptian" -> "🇪🇬"
                "filipino" -> "🇵🇭"
                "french" -> "🇫🇷"
                "greek" -> "🇬🇷"
                "indian" -> "🇮🇳"
                "irish" -> "🇮🇪"
                "italian" -> "🇮🇹"
                "jamaican" -> "🇯🇲"
                "japanese" -> "🇯🇵"
                "kenyan" -> "🇰🇪"
                "malaysian" -> "🇲🇾"
                "mexican" -> "🇲🇽"
                "moroccan" -> "🇲🇦"
                "polish" -> "🇵🇱"
                "portuguese" -> "🇵🇹"
                "russian" -> "🇷🇺"
                "spanish" -> "🇪🇸"
                "thai" -> "🇹🇭"
                "tunisian" -> "🇹🇳"
                "turkish" -> "🇹🇷"
                "ukrainian" -> "🇺🇦"
                "uruguayan" -> "🇺🇾"
                "vietnamese" -> "🇻🇳"
                else -> "🌍" // Default world emoji for unknown areas
            }
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
