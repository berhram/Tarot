package com.velvet.mvi

class TimeTravelMachine<State: ScreenState>(private val onStateSelected: (State) -> Unit) : TimeMachine<State> {
    private val states = mutableListOf<State>()

    override fun addState(state: State) {
        states.add(state)
    }

    override fun selectState(position: Int) {
        onStateSelected(states[position])
    }

    override fun getStates(): List<State> {
        return states
    }
}