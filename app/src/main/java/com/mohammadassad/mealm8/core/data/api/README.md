# Base API Service Documentation

This document explains how to use the Base API Service for adding new features to the MealM8 app.

## Overview

The `BaseApiService` provides a foundation for all API calls in the data layer. It handles:
- HTTP methods (GET, POST, PUT, DELETE)
- Error handling and retries
- URL building with parameters
- JSON serialization/deserialization
- Timeout management

## Architecture

```
BaseApiService (Interface)
    ↓
BaseApiServiceImpl (Implementation)
    ↓
Specific API Services (TheMealDbApiService, ExampleApiService, etc.)
    ↓
Repositories
    ↓
ViewModels
```

## How to Add a New API Feature

### 1. Create Your Data Models

```kotlin
@Serializable
data class YourDataModel(
    val id: String,
    val name: String,
    val description: String? = null
)

@Serializable
data class YourApiResponse(
    val success: Boolean,
    val data: YourDataModel? = null,
    val message: String? = null
)
```

### 2. Create Your API Service

```kotlin
class YourApiService(
    private val baseApiService: BaseApiService
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }
    
    suspend fun getYourData(id: String): Result<YourApiResponse> {
        return baseApiService.get(
            endpoint = "https://api.example.com/data/$id",
            deserializer = { responseBody ->
                json.decodeFromString<YourApiResponse>(responseBody)
            }
        )
    }
    
    suspend fun createYourData(data: YourDataModel): Result<YourApiResponse> {
        return baseApiService.post(
            endpoint = "https://api.example.com/data",
            body = data,
            deserializer = { responseBody ->
                json.decodeFromString<YourApiResponse>(responseBody)
            }
        )
    }
    
    suspend fun searchYourData(query: String, category: String? = null): Result<List<YourDataModel>> {
        val parameters = buildMap {
            put("q", query)
            category?.let { put("category", it) }
        }
        
        return baseApiService.get(
            endpoint = "https://api.example.com/search",
            parameters = parameters,
            deserializer = { responseBody ->
                json.decodeFromString<SearchResponse>(responseBody).results
            }
        )
    }
}
```

### 3. Add to Dependency Injection

```kotlin
// In AppModule.kt
val appModule = module {
    // ... existing dependencies
    
    // Your new API service
    single { YourApiService(get()) }
    
    // Your repository
    single { YourRepository(get()) }
    
    // Your ViewModel
    viewModel { YourViewModel(get()) }
}
```

### 4. Create Repository

```kotlin
class YourRepository(
    private val apiService: YourApiService
) {
    suspend fun getYourData(id: String): Result<YourApiResponse> {
        return apiService.getYourData(id)
    }
    
    suspend fun createYourData(data: YourDataModel): Result<YourApiResponse> {
        return apiService.createYourData(data)
    }
}
```

### 5. Create ViewModel

```kotlin
class YourViewModel(
    private val repository: YourRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(YourUiState())
    val uiState = _uiState.asStateFlow()
    
    fun loadData(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            repository.getYourData(id)
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        data = response.data,
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
}
```

## BaseApiService Methods

### GET Request
```kotlin
suspend fun <T> get(
    endpoint: String,
    parameters: Map<String, String> = emptyMap(),
    deserializer: suspend (String) -> T
): Result<T>
```

### POST Request
```kotlin
suspend fun <T> post(
    endpoint: String,
    body: Any? = null,
    parameters: Map<String, String> = emptyMap(),
    deserializer: suspend (String) -> T
): Result<T>
```

### PUT Request
```kotlin
suspend fun <T> put(
    endpoint: String,
    body: Any? = null,
    parameters: Map<String, String> = emptyMap(),
    deserializer: suspend (String) -> T
): Result<T>
```

### DELETE Request
```kotlin
suspend fun <T> delete(
    endpoint: String,
    parameters: Map<String, String> = emptyMap(),
    deserializer: suspend (String) -> T
): Result<T>
```

## Error Handling

The BaseApiService automatically handles:
- Network timeouts (30 seconds)
- Request retries (3 attempts)
- JSON parsing errors
- HTTP errors

All errors are wrapped in `ApiException` with context about the failed request.

## Examples

See `TheMealDbApiService.kt` for a real-world example of using the BaseApiService.

See `ExampleApiService.kt` for additional examples of different API patterns.

## Best Practices

1. **Always use the BaseApiService** for new API integrations
2. **Create specific data models** for each API response
3. **Handle errors gracefully** in your ViewModels
4. **Use dependency injection** for all services
5. **Keep API services focused** on a single domain
6. **Use repositories** to abstract API calls from ViewModels
7. **Test your API services** with mock data

## Testing

You can easily mock the BaseApiService for testing:

```kotlin
class MockBaseApiService : BaseApiService {
    override suspend fun <T> get(endpoint: String, parameters: Map<String, String>, deserializer: suspend (String) -> T): Result<T> {
        // Return mock data
    }
    // ... implement other methods
}
```
