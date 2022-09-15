package com.velvet.core.viewmodel

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

abstract class ReactiveViewModel<STATE : Any, SIDE_EFFECT : Any> :
    InterceptError,
    ContainerHost<STATE, SIDE_EFFECT>,
    ViewModel() {

    protected suspend fun <T> intercept(block: suspend () -> T): Result<T> = try {
        Result.success(block.invoke())
    } catch (e: Exception) {
        interceptError(e)
        Result.failure(e)
    }
}