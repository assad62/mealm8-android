package com.mohammadassad.mealm8.data.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * TheMealDB API service that extends BaseApiService
 * Provides specific endpoints for TheMealDB API v2
 */
class TheMealDbApiService(
    private val baseApiService: BaseApiService,
    private val apiKey: String
) {
    
    companion object {
        private const val BASE_URL = "https://www.themealdb.com/api/json/v2"
    }
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }
    
    /**
     * Search meals by name
     */
    suspend fun searchMealsByName(query: String): Result<TheMealDbResponse<List<Meal>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/search.php",
            parameters = mapOf("s" to query)
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<Meal>>>(responseBody)
        }
    }
    
    /**
     * Search meals by first letter
     */
    suspend fun searchMealsByFirstLetter(letter: String): Result<TheMealDbResponse<List<Meal>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/search.php",
            parameters = mapOf("f" to letter)
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<Meal>>>(responseBody)
        }
    }
    
    /**
     * Get meal details by ID
     */
    suspend fun getMealById(id: String): Result<TheMealDbResponse<List<Meal>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/lookup.php",
            parameters = mapOf("i" to id)
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<Meal>>>(responseBody)
        }
    }
    
    /**
     * Get random meal
     */
    suspend fun getRandomMeal(): Result<TheMealDbResponse<List<Meal>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/random.php"
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<Meal>>>(responseBody)
        }
    }
    
    /**
     * Get all meal categories
     */
    suspend fun getCategories(): Result<TheMealDbResponse<List<Category>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/categories.php"
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<Category>>>(responseBody)
        }
    }
    
    /**
     * Filter meals by category
     */
    suspend fun filterMealsByCategory(category: String): Result<TheMealDbResponse<List<MealSummary>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/filter.php",
            parameters = mapOf("c" to category)
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<MealSummary>>>(responseBody)
        }
    }
    
    /**
     * Filter meals by area
     */
    suspend fun filterMealsByArea(area: String): Result<TheMealDbResponse<List<MealSummary>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/filter.php",
            parameters = mapOf("a" to area)
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<MealSummary>>>(responseBody)
        }
    }
    
    /**
     * Filter meals by main ingredient
     */
    suspend fun filterMealsByIngredient(ingredient: String): Result<TheMealDbResponse<List<MealSummary>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/filter.php",
            parameters = mapOf("i" to ingredient)
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<MealSummary>>>(responseBody)
        }
    }
    
    /**
     * Get list of categories
     */
    suspend fun getCategoryList(): Result<TheMealDbResponse<List<CategoryItem>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/list.php",
            parameters = mapOf("c" to "list")
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<CategoryItem>>>(responseBody)
        }
    }
    
    /**
     * Get list of areas
     */
    suspend fun getAreaList(): Result<TheMealDbResponse<List<AreaItem>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/list.php",
            parameters = mapOf("a" to "list")
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<AreaItem>>>(responseBody)
        }
    }
    
    /**
     * Get list of ingredients
     */
    suspend fun getIngredientList(): Result<TheMealDbResponse<List<IngredientItem>>> {
        return baseApiService.get(
            endpoint = "$BASE_URL/$apiKey/list.php",
            parameters = mapOf("i" to "list")
        ) { responseBody ->
            json.decodeFromString<TheMealDbResponse<List<IngredientItem>>>(responseBody)
        }
    }
}

/**
 * TheMealDB API response wrapper
 */
@Serializable
data class TheMealDbResponse<T>(
    val meals: T? = null,
    val categories: T? = null
)

/**
 * Complete meal data model
 */
@Serializable
data class Meal(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strDrinkAlternate: String? = null,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strIngredient16: String? = null,
    val strIngredient17: String? = null,
    val strIngredient18: String? = null,
    val strIngredient19: String? = null,
    val strIngredient20: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strMeasure16: String? = null,
    val strMeasure17: String? = null,
    val strMeasure18: String? = null,
    val strMeasure19: String? = null,
    val strMeasure20: String? = null,
    val strSource: String? = null,
    val strImageSource: String? = null,
    val strCreativeCommonsConfirmed: String? = null,
    val dateModified: String? = null
)

/**
 * Meal summary for filtered results
 */
@Serializable
data class MealSummary(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strMealThumb: String? = null
)

/**
 * Category data model
 */
@Serializable
data class Category(
    val idCategory: String? = null,
    val strCategory: String? = null,
    val strCategoryThumb: String? = null,
    val strCategoryDescription: String? = null
)

/**
 * Category list item
 */
@Serializable
data class CategoryItem(
    val strCategory: String? = null
)

/**
 * Area list item
 */
@Serializable
data class AreaItem(
    val strArea: String? = null
)

/**
 * Ingredient list item
 */
@Serializable
data class IngredientItem(
    val strIngredient: String? = null
)
