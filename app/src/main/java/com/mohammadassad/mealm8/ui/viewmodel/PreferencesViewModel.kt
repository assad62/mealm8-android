package com.mohammadassad.mealm8.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadassad.mealm8.data.local.PreferencesManager
import com.mohammadassad.mealm8.data.local.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()
    
    init {
        // Observe user preferences
        viewModelScope.launch {
            preferencesManager.userPreferences.collect { preferences ->
                _uiState.value = _uiState.value.copy(
                    preferences = preferences,
                    isLoading = false
                )
            }
        }
    }
    
    fun updateDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateDarkTheme(isDarkTheme)
        }
    }
    
    fun updateUserName(userName: String) {
        viewModelScope.launch {
            preferencesManager.updateUserName(userName)
        }
    }
    
    fun updateUserEmail(userEmail: String) {
        viewModelScope.launch {
            preferencesManager.updateUserEmail(userEmail)
        }
    }
    
    fun addFavoriteMeal(mealId: String) {
        viewModelScope.launch {
            preferencesManager.addFavoriteMeal(mealId)
        }
    }
    
    fun removeFavoriteMeal(mealId: String) {
        viewModelScope.launch {
            preferencesManager.removeFavoriteMeal(mealId)
        }
    }
    
    fun updateDietaryRestrictions(restrictions: List<String>) {
        viewModelScope.launch {
            preferencesManager.updateDietaryRestrictions(restrictions)
        }
    }
    
    fun updateNotificationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateNotificationEnabled(enabled)
        }
    }
    
    fun updateLanguage(language: String) {
        viewModelScope.launch {
            preferencesManager.updateLanguage(language)
        }
    }
    
    fun markFirstLaunchComplete() {
        viewModelScope.launch {
            preferencesManager.updateFirstLaunch(false)
        }
    }
    
    fun clearAllPreferences() {
        viewModelScope.launch {
            preferencesManager.clearAllPreferences()
        }
    }
}

data class PreferencesUiState(
    val isLoading: Boolean = true,
    val preferences: UserPreferences = UserPreferences(),
    val error: String? = null
)

