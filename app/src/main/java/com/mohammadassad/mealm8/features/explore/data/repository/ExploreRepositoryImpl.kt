package com.mohammadassad.mealm8.features.explore.data.repository

import com.mohammadassad.mealm8.core.data.api.TheMealDbResponse
import com.mohammadassad.mealm8.core.data.api.CategoryItem
import com.mohammadassad.mealm8.core.data.api.AreaItem
import com.mohammadassad.mealm8.core.data.api.IngredientItem
import com.mohammadassad.mealm8.features.explore.domain.model.ExploreItem
import com.mohammadassad.mealm8.features.explore.domain.model.ExploreType
import com.mohammadassad.mealm8.features.explore.domain.repository.ExploreRepository

/**
 * Repository implementation for explore functionality
 * Handles data access for Categories, Areas, and Ingredients
 */
class ExploreRepositoryImpl(
    private val theMealDbApiService: com.mohammadassad.mealm8.core.data.api.TheMealDbApiService
) : ExploreRepository {
    
    override suspend fun getCategories(): Result<List<ExploreItem>> {
        return theMealDbApiService.getCategoryList()
            .map { response ->
                response.meals?.map { categoryItem ->
                    ExploreItem.fromCategory(categoryItem)
                } ?: emptyList()
            }
    }
    
    override suspend fun getAreas(): Result<List<ExploreItem>> {
        return theMealDbApiService.getAreaList()
            .map { response ->
                response.meals?.map { areaItem ->
                    ExploreItem.fromArea(areaItem)
                } ?: emptyList()
            }
    }
    
    override suspend fun getIngredients(): Result<List<ExploreItem>> {
        return theMealDbApiService.getIngredientList()
            .map { response ->
                response.meals?.map { ingredientItem ->
                    ExploreItem.fromIngredient(ingredientItem)
                } ?: emptyList()
            }
    }
    
    override suspend fun getAllExploreData(): Result<Map<ExploreType, List<ExploreItem>>> {
        return try {
            val categoriesResult = getCategories()
            val areasResult = getAreas()
            val ingredientsResult = getIngredients()
            
            if (categoriesResult.isSuccess && areasResult.isSuccess && ingredientsResult.isSuccess) {
                Result.success(
                    mapOf(
                        ExploreType.CATEGORY to categoriesResult.getOrThrow(),
                        ExploreType.AREA to areasResult.getOrThrow(),
                        ExploreType.INGREDIENT to ingredientsResult.getOrThrow()
                    )
                )
            } else {
                val error = listOfNotNull(
                    categoriesResult.exceptionOrNull(),
                    areasResult.exceptionOrNull(),
                    ingredientsResult.exceptionOrNull()
                ).firstOrNull()
                Result.failure(error ?: Exception("Failed to load explore data"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
