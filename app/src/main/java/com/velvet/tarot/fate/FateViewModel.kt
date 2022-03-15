package com.velvet.tarot.fate

import androidx.lifecycle.viewModelScope
import com.velvet.models.network.Network
import com.velvet.mvi.BaseViewModel
import com.velvet.mvi.Reducer
import com.velvet.mvi.TimeMachine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FateViewModel constructor(
    private val repo: Network,
    private val dispatcher: CoroutineDispatcher) : BaseViewModel<FateScreenState, FateScreenEvent>() {
    private val reducer = FateReducer(FateScreenState.initial())

    override val state: Flow<FateScreenState>
        get() = reducer.state

    val timeMachine: TimeMachine<FateScreenState>
        get() = reducer.timeMachine

    init {
        viewModelScope.launch(dispatcher) {
            sendEvent(FateScreenEvent.Initial)
        }
    }

    private fun sendEvent(event: FateScreenEvent) {
        reducer.sendEvent(event)
    }

    fun onOneCardGet() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.getCards(1)
            sendEvent(FateScreenEvent.OnOneCardButtonClicked(cards = data))
        }
    }

    fun onThreeCardGet() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.getCards(3)
            sendEvent(FateScreenEvent.OnThreeCardButtonClicked(cards = data))
        }
    }

    private class FateReducer(initState: FateScreenState) : Reducer<FateScreenState, FateScreenEvent>(initState = initState) {
        override fun reduce(oldState: FateScreenState, event: FateScreenEvent) {
            when (event) {
                is FateScreenEvent.OnOneCardButtonClicked -> {
                    setState(oldState.copy(isLoading = true, data = event.cards))
                }
                is FateScreenEvent.OnThreeCardButtonClicked -> {
                    setState(oldState.copy(isLoading = true, data = event.cards))
                }
            }
        }
    }
}