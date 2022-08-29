package com.velvet.core.viewmodel

interface InterceptError {

    suspend fun interceptError(error: Exception)
}