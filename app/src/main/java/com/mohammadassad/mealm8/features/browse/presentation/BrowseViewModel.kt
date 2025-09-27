package com.mohammadassad.mealm8.features.browse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadassad.mealm8.features.browse.domain.model.BrowseItem
import com.mohammadassad.mealm8.features.browse.domain.model.BrowseType
import com.mohammadassad.mealm8.features.browse.domain.usecase.GetAllBrowseDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BrowseUiState(
    val selectedTab: BrowseType = BrowseType.CATEGORY,
    val categories: List<BrowseItem> = emptyList(),
    val areas: List<BrowseItem> = emptyList(),
    val ingredients: List<BrowseItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val currentItems: List<BrowseItem>
        get() = when (selectedTab) {
            BrowseType.CATEGORY -> categories
            BrowseType.AREA -> areas
            BrowseType.INGREDIENT -> ingredients
        }
    
    val currentTitle: String
        get() = when (selectedTab) {
            BrowseType.CATEGORY -> "Categories"
            BrowseType.AREA -> "Cuisines"
            BrowseType.INGREDIENT -> "Ingredients"
        }
}

class BrowseViewModel(
    private val getAllBrowseDataUseCase: GetAllBrowseDataUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(BrowseUiState())
    val uiState: StateFlow<BrowseUiState> = _uiState.asStateFlow()
    
    init {
        loadAllBrowseData()
    }
    
    fun selectTab(tab: BrowseType) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }
    
    fun retry() {
        loadAllBrowseData()
    }
    
    private fun loadAllBrowseData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            getAllBrowseDataUseCase()
                .onSuccess { data ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        categories = data[BrowseType.CATEGORY] ?: emptyList(),
                        areas = data[BrowseType.AREA] ?: emptyList(),
                        ingredients = data[BrowseType.INGREDIENT] ?: emptyList(),
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Failed to load browse data"
                    )
                }
        }
    }
}
