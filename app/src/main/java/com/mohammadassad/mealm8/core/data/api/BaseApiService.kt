package com.mohammadassad.mealm8.core.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

/**
 * Base API service interface that provides common functionality for all API services
 */
interface BaseApiService {
    suspend fun <T> get(
        endpoint: String,
        parameters: Map<String, String> = emptyMap(),
        deserializer: suspend (String) -> T
    ): Result<T>
    
    suspend fun <T> post(
        endpoint: String,
        body: Any? = null,
        parameters: Map<String, String> = emptyMap(),
        deserializer: suspend (String) -> T
    ): Result<T>
    
    suspend fun <T> put(
        endpoint: String,
        body: Any? = null,
        parameters: Map<String, String> = emptyMap(),
        deserializer: suspend (String) -> T
    ): Result<T>
    
    suspend fun <T> delete(
        endpoint: String,
        parameters: Map<String, String> = emptyMap(),
        deserializer: suspend (String) -> T
    ): Result<T>
}

/**
 * Base API service implementation with common functionality
 */
class BaseApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String
) : BaseApiService {
    
    override suspend fun <T> get(
        endpoint: String,
        parameters: Map<String, String>,
        deserializer: suspend (String) -> T
    ): Result<T> {
        return makeRequest(
            method = HttpMethod.Get,
            endpoint = endpoint,
            parameters = parameters,
            deserializer = deserializer
        )
    }
    
    override suspend fun <T> post(
        endpoint: String,
        body: Any?,
        parameters: Map<String, String>,
        deserializer: suspend (String) -> T
    ): Result<T> {
        return makeRequest(
            method = HttpMethod.Post,
            endpoint = endpoint,
            body = body,
            parameters = parameters,
            deserializer = deserializer
        )
    }
    
    override suspend fun <T> put(
        endpoint: String,
        body: Any?,
        parameters: Map<String, String>,
        deserializer: suspend (String) -> T
    ): Result<T> {
        return makeRequest(
            method = HttpMethod.Put,
            endpoint = endpoint,
            body = body,
            parameters = parameters,
            deserializer = deserializer
        )
    }
    
    override suspend fun <T> delete(
        endpoint: String,
        parameters: Map<String, String>,
        deserializer: suspend (String) -> T
    ): Result<T> {
        return makeRequest(
            method = HttpMethod.Delete,
            endpoint = endpoint,
            parameters = parameters,
            deserializer = deserializer
        )
    }
    
    private suspend fun <T> makeRequest(
        method: HttpMethod,
        endpoint: String,
        body: Any? = null,
        parameters: Map<String, String> = emptyMap(),
        deserializer: suspend (String) -> T
    ): Result<T> {
        return try {
            val url = buildUrl(endpoint, parameters)
            
            val response = when (method) {
                HttpMethod.Get -> client.get(url)
                HttpMethod.Post -> client.post(url) {
                    body?.let { setBody(it) }
                }
                HttpMethod.Put -> client.put(url) {
                    body?.let { setBody(it) }
                }
                HttpMethod.Delete -> client.delete(url)
                else -> throw IllegalArgumentException("Unsupported HTTP method: $method")
            }
            
            val responseBody = response.body<String>()
            val result = deserializer(responseBody)
            Result.success(result)
            
        } catch (e: Exception) {
            Result.failure(
                ApiException(
                    message = "API request failed: ${e.message}",
                    cause = e,
                    endpoint = endpoint,
                    method = method.value
                )
            )
        }
    }
    
    private fun buildUrl(endpoint: String, parameters: Map<String, String>): String {
        val url = if (endpoint.startsWith("http")) {
            endpoint
        } else {
            "$baseUrl/$endpoint".removeSuffix("/")
        }
        
        return if (parameters.isNotEmpty()) {
            val queryString = parameters.entries.joinToString("&") { (key, value) ->
                "$key=${value.encodeURLParameter()}"
            }
            "$url?$queryString"
        } else {
            url
        }
    }
}

/**
 * Custom exception for API-related errors
 */
data class ApiException(
    override val message: String,
    override val cause: Throwable? = null,
    val endpoint: String? = null,
    val method: String? = null,
    val statusCode: Int? = null
) : Exception(message, cause)

/**
 * Extension function to encode URL parameters
 */
private fun String.encodeURLParameter(): String {
    return java.net.URLEncoder.encode(this, "UTF-8")
}
