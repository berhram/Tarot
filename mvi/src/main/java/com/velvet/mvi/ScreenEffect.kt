package com.velvet.mvi

interface ScreenEffect<T> {
    fun visit(screen: T)
}