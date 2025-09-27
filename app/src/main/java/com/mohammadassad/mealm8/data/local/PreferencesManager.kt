package com.mohammadassad.mealm8.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class PreferencesManager(private val context: Context) {
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    
    // Keys for different preference types
    private object PreferencesKeys {
        val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val FAVORITE_MEALS = stringPreferencesKey("favorite_meals")
        val DIETARY_RESTRICTIONS = stringPreferencesKey("dietary_restrictions")
        val NOTIFICATION_ENABLED = booleanPreferencesKey("notification_enabled")
        val LANGUAGE = stringPreferencesKey("language")
        val LAST_SYNC_TIME = longPreferencesKey("last_sync_time")
    }
    
    // Get all user preferences as a flow
    val userPreferences: Flow<UserPreferences> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                isFirstLaunch = preferences[PreferencesKeys.IS_FIRST_LAUNCH] ?: true,
                isDarkTheme = preferences[PreferencesKeys.IS_DARK_THEME] ?: false,
                userName = preferences[PreferencesKeys.USER_NAME] ?: "",
                userEmail = preferences[PreferencesKeys.USER_EMAIL] ?: "",
                favoriteMeals = preferences[PreferencesKeys.FAVORITE_MEALS]?.let { 
                    json.decodeFromString<List<String>>(it) 
                } ?: emptyList(),
                dietaryRestrictions = preferences[PreferencesKeys.DIETARY_RESTRICTIONS]?.let { 
                    json.decodeFromString<List<String>>(it) 
                } ?: emptyList(),
                notificationEnabled = preferences[PreferencesKeys.NOTIFICATION_ENABLED] ?: true,
                language = preferences[PreferencesKeys.LANGUAGE] ?: "en",
                lastSyncTime = preferences[PreferencesKeys.LAST_SYNC_TIME] ?: 0L
            )
        }
    
    // Individual preference getters
    val isFirstLaunch: Flow<Boolean> = context.dataStore.data
        .map { it[PreferencesKeys.IS_FIRST_LAUNCH] ?: true }
    
    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { it[PreferencesKeys.IS_DARK_THEME] ?: false }
    
    val userName: Flow<String> = context.dataStore.data
        .map { it[PreferencesKeys.USER_NAME] ?: "" }
    
    val userEmail: Flow<String> = context.dataStore.data
        .map { it[PreferencesKeys.USER_EMAIL] ?: "" }
    
    val favoriteMeals: Flow<List<String>> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.FAVORITE_MEALS]?.let { 
                json.decodeFromString<List<String>>(it) 
            } ?: emptyList()
        }
    
    val dietaryRestrictions: Flow<List<String>> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.DIETARY_RESTRICTIONS]?.let { 
                json.decodeFromString<List<String>>(it) 
            } ?: emptyList()
        }
    
    val notificationEnabled: Flow<Boolean> = context.dataStore.data
        .map { it[PreferencesKeys.NOTIFICATION_ENABLED] ?: true }
    
    val language: Flow<String> = context.dataStore.data
        .map { it[PreferencesKeys.LANGUAGE] ?: "en" }
    
    val lastSyncTime: Flow<Long> = context.dataStore.data
        .map { it[PreferencesKeys.LAST_SYNC_TIME] ?: 0L }
    
    // Update methods
    suspend fun updateFirstLaunch(isFirstLaunch: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_FIRST_LAUNCH] = isFirstLaunch
        }
    }
    
    suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_THEME] = isDarkTheme
        }
    }
    
    suspend fun updateUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = userName
        }
    }
    
    suspend fun updateUserEmail(userEmail: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_EMAIL] = userEmail
        }
    }
    
    suspend fun updateFavoriteMeals(favoriteMeals: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.FAVORITE_MEALS] = json.encodeToString(favoriteMeals)
        }
    }
    
    suspend fun addFavoriteMeal(mealId: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[PreferencesKeys.FAVORITE_MEALS]?.let { 
                json.decodeFromString<List<String>>(it) 
            } ?: emptyList()
            val updatedFavorites = currentFavorites + mealId
            preferences[PreferencesKeys.FAVORITE_MEALS] = json.encodeToString(updatedFavorites)
        }
    }
    
    suspend fun removeFavoriteMeal(mealId: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[PreferencesKeys.FAVORITE_MEALS]?.let { 
                json.decodeFromString<List<String>>(it) 
            } ?: emptyList()
            val updatedFavorites = currentFavorites.filter { it != mealId }
            preferences[PreferencesKeys.FAVORITE_MEALS] = json.encodeToString(updatedFavorites)
        }
    }
    
    suspend fun updateDietaryRestrictions(dietaryRestrictions: List<String>) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.DIETARY_RESTRICTIONS] = json.encodeToString(dietaryRestrictions)
        }
    }
    
    suspend fun updateNotificationEnabled(notificationEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATION_ENABLED] = notificationEnabled
        }
    }
    
    suspend fun updateLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = language
        }
    }
    
    suspend fun updateLastSyncTime(lastSyncTime: Long) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_SYNC_TIME] = lastSyncTime
        }
    }
    
    // Clear all preferences
    suspend fun clearAllPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

