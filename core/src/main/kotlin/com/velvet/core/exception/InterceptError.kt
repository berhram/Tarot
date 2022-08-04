package com.velvet.core.exception

interface InterceptError {

    suspend fun interceptError(error: Exception)
}