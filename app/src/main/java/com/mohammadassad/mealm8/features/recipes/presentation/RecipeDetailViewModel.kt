package com.mohammadassad.mealm8.features.recipes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadassad.mealm8.core.data.repository.MealRepository
import com.mohammadassad.mealm8.features.recipes.domain.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val error: String? = null
)

class RecipeDetailViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState: StateFlow<RecipeDetailUiState> = _uiState.asStateFlow()
    
    fun loadRecipe(mealId: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )
        
        viewModelScope.launch {
            try {
                val result = mealRepository.getMealById(mealId)
                
                result.fold(
                    onSuccess = { response ->
                        val meal = response.meals?.firstOrNull()
                        if (meal != null) {
                            val recipe = Recipe.fromApiModel(meal)
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                recipe = recipe,
                                error = null
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = "Recipe not found"
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to load recipe"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }
    
    fun retry() {
        val currentRecipe = _uiState.value.recipe
        if (currentRecipe != null) {
            loadRecipe(currentRecipe.id)
        }
    }
}
