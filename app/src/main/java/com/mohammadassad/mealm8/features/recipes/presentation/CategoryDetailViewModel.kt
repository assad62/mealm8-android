package com.mohammadassad.mealm8.features.recipes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadassad.mealm8.core.data.repository.MealRepository
import com.mohammadassad.mealm8.features.recipes.domain.model.MealSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CategoryDetailUiState(
    val isLoading: Boolean = false,
    val meals: List<MealSummary> = emptyList(),
    val error: String? = null,
    val categoryName: String = "",
    val categoryType: String = ""
)

class CategoryDetailViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CategoryDetailUiState())
    val uiState: StateFlow<CategoryDetailUiState> = _uiState.asStateFlow()
    
    fun loadMeals(type: String, name: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null,
            categoryName = name,
            categoryType = type
        )
        
        viewModelScope.launch {
            try {
                val result = when (type.lowercase()) {
                    "category" -> mealRepository.filterMealsByCategory(name)
                    "area" -> mealRepository.filterMealsByArea(name)
                    "ingredient" -> mealRepository.filterMealsByIngredient(name)
                    else -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "Unknown category type: $type"
                        )
                        return@launch
                    }
                }
                
                result.fold(
                    onSuccess = { response ->
                        val meals = response.meals?.map { MealSummary.fromApiModel(it) } ?: emptyList()
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            meals = meals,
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to load meals"
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
        val currentState = _uiState.value
        loadMeals(currentState.categoryType, currentState.categoryName)
    }
}
