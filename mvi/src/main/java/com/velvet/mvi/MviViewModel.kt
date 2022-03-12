package com.velvet.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MviViewModel<V, S : ScreenState<V, S>, E> : ViewModel(),
    FragmentContract.ViewModel<S, E> {
    private val stateHolder = MutableLiveData<S>()
    private val effectHolder = MutableLiveData<E>()

    override fun getStateObservable() = stateHolder

    override fun getEffectObservable() = effectHolder

    protected fun setState(state: S) {
        stateHolder.value?.let { state.merge(it) }
        stateHolder.value = state
    }

    protected fun getState() = stateHolder.value

    protected fun setAction(action: E) {
        effectHolder.value = action
    }

    override fun onStateChanged(event: Lifecycle.Event) {
    }
}