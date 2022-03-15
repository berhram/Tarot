package com.velvet.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<State: ScreenState, Event: ScreenEvent>(initState: State) {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initState)
    val state: StateFlow<State>
        get() = _state

    //time machine that stores states for debugging
    val timeMachine: TimeMachine<State> = TimeTravelMachine {
        storedState -> _state.tryEmit(storedState)
    }

    init {
        timeMachine.addState(initState)
    }

    fun setState(newState: State) {
        val success = _state.tryEmit(newState)

        if (BuildConfig.DEBUG && success) {
            timeMachine.addState(newState)
        }
    }

    fun sendEvent(event: Event) {
        reduce(_state.value, event)
    }

    abstract fun reduce(oldState: State, event: Event)
}