package com.velvet.coremvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.orbitmvi.orbit.ContainerHost

abstract class ReactiveViewModel<STATE : State, EFFECT : Effect> : ContainerHost<STATE, EFFECT>, SafeCall, ViewModel() {

    protected suspend fun <T> backgroundCall(call: suspend () -> T, dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        viewModelScope.async(dispatcher) { safeCall { call() } }
}