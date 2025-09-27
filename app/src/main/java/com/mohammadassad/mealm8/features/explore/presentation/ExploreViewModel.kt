package com.mohammadassad.mealm8.features.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadassad.mealm8.features.explore.domain.model.ExploreItem
import com.mohammadassad.mealm8.features.explore.domain.model.ExploreType
import com.mohammadassad.mealm8.features.explore.domain.usecase.GetAllExploreDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ExploreUiState(
    val selectedTab: ExploreType = ExploreType.CATEGORY,
    val categories: List<ExploreItem> = emptyList(),
    val areas: List<ExploreItem> = emptyList(),
    val ingredients: List<ExploreItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val currentItems: List<ExploreItem>
        get() = when (selectedTab) {
            ExploreType.CATEGORY -> categories
            ExploreType.AREA -> areas
            ExploreType.INGREDIENT -> ingredients
        }
    
    val currentTitle: String
        get() = when (selectedTab) {
            ExploreType.CATEGORY -> "Browse by Category"
            ExploreType.AREA -> "Explore Cuisines"
            ExploreType.INGREDIENT -> "Browse Ingredients"
        }
}

class ExploreViewModel(
    private val getAllExploreDataUseCase: GetAllExploreDataUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()
    
    init {
        loadAllExploreData()
    }
    
    fun selectTab(tab: ExploreType) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }
    
    fun retry() {
        loadAllExploreData()
    }
    
    private fun loadAllExploreData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            getAllExploreDataUseCase()
                .onSuccess { data ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        categories = data[ExploreType.CATEGORY] ?: emptyList(),
                        areas = data[ExploreType.AREA] ?: emptyList(),
                        ingredients = data[ExploreType.INGREDIENT] ?: emptyList(),
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load explore data"
                    )
                }
        }
    }
}
