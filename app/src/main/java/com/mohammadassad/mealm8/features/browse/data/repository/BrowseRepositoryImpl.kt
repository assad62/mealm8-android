package com.mohammadassad.mealm8.features.browse.data.repository

import com.mohammadassad.mealm8.core.data.api.TheMealDbResponse
import com.mohammadassad.mealm8.core.data.api.CategoryItem
import com.mohammadassad.mealm8.core.data.api.AreaItem
import com.mohammadassad.mealm8.core.data.api.IngredientItem
import com.mohammadassad.mealm8.features.browse.domain.model.BrowseItem
import com.mohammadassad.mealm8.features.browse.domain.model.BrowseType
import com.mohammadassad.mealm8.features.browse.domain.repository.BrowseRepository

/**
 * Repository implementation for browse functionality
 * Handles data access for Categories, Areas, and Ingredients
 */
class BrowseRepositoryImpl(
    private val theMealDbApiService: com.mohammadassad.mealm8.core.data.api.TheMealDbApiService
) : BrowseRepository {
    
    override suspend fun getCategories(): Result<List<BrowseItem>> {
        return theMealDbApiService.getCategoryList()
            .map { response ->
                response.meals?.map { categoryItem ->
                    BrowseItem.fromCategory(categoryItem)
                } ?: emptyList()
            }
    }
    
    override suspend fun getAreas(): Result<List<BrowseItem>> {
        return theMealDbApiService.getAreaList()
            .map { response ->
                response.meals?.map { areaItem ->
                    BrowseItem.fromArea(areaItem)
                } ?: emptyList()
            }
    }
    
    override suspend fun getIngredients(): Result<List<BrowseItem>> {
        return theMealDbApiService.getIngredientList()
            .map { response ->
                response.meals?.map { ingredientItem ->
                    BrowseItem.fromIngredient(ingredientItem)
                } ?: emptyList()
            }
    }
    
    override suspend fun getAllBrowseData(): Result<Map<BrowseType, List<BrowseItem>>> {
        return try {
            val categoriesResult = getCategories()
            val areasResult = getAreas()
            val ingredientsResult = getIngredients()
            
            if (categoriesResult.isSuccess && areasResult.isSuccess && ingredientsResult.isSuccess) {
                Result.success(
                    mapOf(
                        BrowseType.CATEGORY to categoriesResult.getOrThrow(),
                        BrowseType.AREA to areasResult.getOrThrow(),
                        BrowseType.INGREDIENT to ingredientsResult.getOrThrow()
                    )
                )
            } else {
                val error = listOfNotNull(
                    categoriesResult.exceptionOrNull(),
                    areasResult.exceptionOrNull(),
                    ingredientsResult.exceptionOrNull()
                ).firstOrNull()
                Result.failure(error ?: Exception("Failed to load browse data"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
