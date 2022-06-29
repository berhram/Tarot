package com.velvet.coremvi

interface SafeCall {
    suspend fun <T> safeCall(
        call: suspend () -> T
    ): Result<T> {
        return try {
            Result.success(call.invoke())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}