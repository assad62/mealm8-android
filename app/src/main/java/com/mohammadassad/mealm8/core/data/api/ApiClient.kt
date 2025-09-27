package com.mohammadassad.mealm8.core.data.api

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiClient {
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = false
        coerceInputValues = true
        encodeDefaults = true
    }
    
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(json)
        }
        
        install(Logging) {
            level = LogLevel.INFO
        }
        
        install(DefaultRequest) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
        }
        
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 30000
            socketTimeoutMillis = 30000
        }
        
    }
}


