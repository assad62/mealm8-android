package com.mohammadassad.mealm8.core.data.local

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val isFirstLaunch: Boolean = true,
    val isDarkTheme: Boolean = false,
    val userName: String = "",
    val userEmail: String = "",
    val favoriteMeals: List<String> = emptyList(),
    val dietaryRestrictions: List<String> = emptyList(),
    val notificationEnabled: Boolean = true,
    val language: String = "en",
    val lastSyncTime: Long = 0L
)


