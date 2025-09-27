package com.mohammadassad.mealm8

import android.app.Application
import com.mohammadassad.mealm8.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MealM8Application : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@MealM8Application)
            modules(appModule)
        }
    }
}
