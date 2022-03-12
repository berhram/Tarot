package com.velvet.mvi

abstract class AbstractEffect<T> : ScreenEffect<T> {
    var isHandled = false

    override fun visit(screen: T) {
        if (!isHandled) {
            handle(screen);
            isHandled = true
        }
    }

    abstract fun handle(screen: T);
}