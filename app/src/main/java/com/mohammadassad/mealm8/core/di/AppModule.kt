package com.mohammadassad.mealm8.core.di

import android.content.Context
import com.mohammadassad.mealm8.core.data.api.ApiClient
import com.mohammadassad.mealm8.core.data.api.BaseApiService
import com.mohammadassad.mealm8.core.data.api.BaseApiServiceImpl
import com.mohammadassad.mealm8.core.data.api.TheMealDbApiService
import com.mohammadassad.mealm8.core.data.local.PreferencesManager
import com.mohammadassad.mealm8.core.data.repository.MealRepository
import com.mohammadassad.mealm8.features.categories.data.repository.CategoriesRepositoryImpl
import com.mohammadassad.mealm8.features.categories.domain.repository.CategoriesRepository
import com.mohammadassad.mealm8.features.categories.domain.usecase.GetCategoriesUseCase
import com.mohammadassad.mealm8.features.categories.presentation.CategoriesViewModel
import com.mohammadassad.mealm8.features.explore.data.repository.ExploreRepositoryImpl
import com.mohammadassad.mealm8.features.explore.domain.repository.ExploreRepository
import com.mohammadassad.mealm8.features.explore.domain.usecase.GetAllExploreDataUseCase
import com.mohammadassad.mealm8.features.explore.presentation.ExploreViewModel
import com.mohammadassad.mealm8.features.search.data.repository.SearchRepositoryImpl
import com.mohammadassad.mealm8.features.search.domain.repository.SearchRepository
import com.mohammadassad.mealm8.features.search.domain.usecase.SearchUseCase
import com.mohammadassad.mealm8.features.search.presentation.SearchViewModel
import com.mohammadassad.mealm8.features.recipes.presentation.CategoryDetailViewModel
import com.mohammadassad.mealm8.features.recipes.presentation.RecipeDetailViewModel
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
    
    // Categories Feature
    single<CategoriesRepository> { CategoriesRepositoryImpl(get()) }
    single { GetCategoriesUseCase(get()) }
    viewModel { CategoriesViewModel(get()) }
    
    // Explore Feature
    single<ExploreRepository> { ExploreRepositoryImpl(get()) }
    single { GetAllExploreDataUseCase(get()) }
    viewModel { ExploreViewModel(get()) }
    
    // Search Feature
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single { SearchUseCase(get()) }
    viewModel { SearchViewModel(get()) }
    
    // Recipe Detail Feature
    viewModel { CategoryDetailViewModel(get()) }
    viewModel { RecipeDetailViewModel(get()) }
    
    // Add other dependencies here as needed
    // Example:
    // factory<UseCase> { UseCaseImpl(get()) }
}
