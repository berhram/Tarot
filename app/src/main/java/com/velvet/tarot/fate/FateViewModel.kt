package com.velvet.tarot.fate

import com.velvet.models.network.Network
import com.velvet.mvi.MviViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

class FateViewModel(private val repo: Network) : MviViewModel<FateContract.View, FateScreenState, FateScreenEffect>(),
    FateContract.ViewModel {

    private val tarotIdsFlow = MutableStateFlow(-1L)

    private var launch: Job? = null


    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        setAction(FateScreenEffect.Error(e))
    }

}