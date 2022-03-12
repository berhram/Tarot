package com.velvet.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData

class FragmentContract {
    interface ViewModel<S, E> {
        fun onStateChanged(event: Lifecycle.Event)
        fun getStateObservable(): LiveData<S>
        fun getEffectObservable(): LiveData<E>
    }

    interface View

    interface Host
}