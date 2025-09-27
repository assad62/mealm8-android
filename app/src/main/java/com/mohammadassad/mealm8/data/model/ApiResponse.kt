package com.mohammadassad.mealm8.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val error: String? = null
)

@Serializable
data class ErrorResponse(
    val error: String,
    val message: String,
    val statusCode: Int
)


