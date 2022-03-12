package com.velvet.mvi

interface ScreenState<T, S> {
    fun visit(screen: T)

    fun merge(prevState: S)
}