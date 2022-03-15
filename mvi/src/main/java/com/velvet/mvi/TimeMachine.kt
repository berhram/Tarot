package com.velvet.mvi

interface TimeMachine<State: ScreenState> {
    fun addState(state: State)
    fun selectState(position: Int)
    fun getStates(): List<State>
}