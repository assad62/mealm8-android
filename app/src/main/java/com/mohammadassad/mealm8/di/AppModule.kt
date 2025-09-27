package com.mohammadassad.mealm8.di

import android.content.Context
import com.mohammadassad.mealm8.data.api.ApiClient
import com.mohammadassad.mealm8.data.api.BaseApiService
import com.mohammadassad.mealm8.data.api.BaseApiServiceImpl
import com.mohammadassad.mealm8.data.api.TheMealDbApiService
import com.mohammadassad.mealm8.data.local.PreferencesManager
import com.mohammadassad.mealm8.data.repository.MealRepository
import com.mohammadassad.mealm8.ui.viewmodel.MealViewModel
import com.mohammadassad.mealm8.ui.viewmodel.PreferencesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // HTTP Client
    single { ApiClient.client }
    
    // DataStore
    single { PreferencesManager(androidContext()) }
    
    // Base API Service - can be used for any API
    single<BaseApiService> { 
        BaseApiServiceImpl(
            client = get(),
            baseUrl = "" // Empty base URL for flexibility
        )
    }
    
    // TheMealDB API Service
    single { 
        TheMealDbApiService(
            baseApiService = get(),
            apiKey = com.mohammadassad.mealm8.BuildConfig.THEMEALDB_API_KEY
        )
    }
    
    // Repositories
    single { MealRepository(get()) }
    
    // ViewModels
    viewModel { MealViewModel(get()) }
    viewModel { PreferencesViewModel(get()) }
    
    // Add other dependencies here as needed
    // Example:
    // factory<UseCase> { UseCaseImpl(get()) }
}
