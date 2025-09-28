package com.mohammadassad.mealm8.features.recipes.domain.model

/**
 * Domain model for Recipe
 * This represents the business entity for a complete recipe
 */
data class Recipe(
    val id: String,
    val name: String,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val imageUrl: String?,
    val youtubeUrl: String?,
    val sourceUrl: String?,
    val tags: List<String>,
    val ingredients: List<Ingredient>
) {
    companion object {
        fun fromApiModel(apiModel: com.mohammadassad.mealm8.core.data.api.Meal): Recipe {
            val ingredients = mutableListOf<Ingredient>()
            
            // Extract ingredients and measurements - access properties directly
            val ingredientList = listOf(
                apiModel.strIngredient1, apiModel.strIngredient2, apiModel.strIngredient3, apiModel.strIngredient4, apiModel.strIngredient5,
                apiModel.strIngredient6, apiModel.strIngredient7, apiModel.strIngredient8, apiModel.strIngredient9, apiModel.strIngredient10,
                apiModel.strIngredient11, apiModel.strIngredient12, apiModel.strIngredient13, apiModel.strIngredient14, apiModel.strIngredient15,
                apiModel.strIngredient16, apiModel.strIngredient17, apiModel.strIngredient18, apiModel.strIngredient19, apiModel.strIngredient20
            )
            
            val measureList = listOf(
                apiModel.strMeasure1, apiModel.strMeasure2, apiModel.strMeasure3, apiModel.strMeasure4, apiModel.strMeasure5,
                apiModel.strMeasure6, apiModel.strMeasure7, apiModel.strMeasure8, apiModel.strMeasure9, apiModel.strMeasure10,
                apiModel.strMeasure11, apiModel.strMeasure12, apiModel.strMeasure13, apiModel.strMeasure14, apiModel.strMeasure15,
                apiModel.strMeasure16, apiModel.strMeasure17, apiModel.strMeasure18, apiModel.strMeasure19, apiModel.strMeasure20
            )
            
            ingredientList.forEachIndexed { index, ingredientName ->
                if (!ingredientName.isNullOrBlank()) {
                    val measure = measureList[index]?.takeIf { it.isNotBlank() } ?: ""
                    ingredients.add(
                        Ingredient(
                            name = ingredientName,
                            measure = measure
                        )
                    )
                }
            }
            
            val tags = apiModel.strTags?.split(",")?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList()
            
            return Recipe(
                id = apiModel.idMeal ?: "",
                name = apiModel.strMeal ?: "",
                category = apiModel.strCategory,
                area = apiModel.strArea,
                instructions = apiModel.strInstructions,
                imageUrl = apiModel.strMealThumb,
                youtubeUrl = apiModel.strYoutube,
                sourceUrl = apiModel.strSource,
                tags = tags,
                ingredients = ingredients
            )
        }
    }
}

/**
 * Domain model for Recipe Ingredient
 */
data class Ingredient(
    val name: String,
    val measure: String
)

/**
 * Extension functions for URL validation
 */
fun String?.isValidUrl(): Boolean {
    return !isNullOrBlank() && (this!!.startsWith("http://") || this.startsWith("https://"))
}

fun String?.isValidYouTubeUrl(): Boolean {
    return isValidUrl() && (this!!.contains("youtube.com") || this.contains("youtu.be"))
}
