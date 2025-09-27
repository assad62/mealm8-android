package com.mohammadassad.mealm8.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadassad.mealm8.features.search.domain.model.SearchResult
import com.mohammadassad.mealm8.features.search.domain.model.SearchType
import com.mohammadassad.mealm8.features.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SearchUiState(
    val query: String = "",
    val selectedType: SearchType = SearchType.MEALS,
    val results: List<SearchResult> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
        if (query.isNotBlank()) {
            performSearch()
        } else {
            _uiState.value = _uiState.value.copy(results = emptyList())
        }
    }

    fun selectSearchType(type: SearchType) {
        _uiState.value = _uiState.value.copy(selectedType = type)
        if (_uiState.value.query.isNotBlank()) {
            performSearch()
        }
    }

    fun performSearch() {
        val query = _uiState.value.query
        val type = _uiState.value.selectedType
        
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(results = emptyList())
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            searchUseCase(query, type)
                .onSuccess { results ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        results = results,
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Search failed"
                    )
                }
        }
    }

    fun retry() {
        performSearch()
    }
}
