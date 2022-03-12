package com.velvet.mvi

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import java.lang.reflect.ParameterizedType

abstract class HostedFragment<
        VIEW : FragmentContract.View,
        STATE : ScreenState<VIEW, STATE>,
        EFFECT : ScreenEffect<VIEW>,
        VIEW_MODEL : FragmentContract.ViewModel<STATE, EFFECT>,
        HOST : FragmentContract.Host>
    : NavHostFragment(), FragmentContract.View, Observer<STATE>, LifecycleEventObserver {

    protected var model: VIEW_MODEL? = null
        private set

    protected var fragmentHost: HOST? = null
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentHost = try {
            context as HOST
        } catch (e: Throwable) {
            val hostClassName = ((javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[1] as Class<*>).canonicalName
            throw RuntimeException("Activity must implement " + hostClassName
                    + " to attach " + this.javaClass.simpleName, e)
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentHost = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setModel(createModel())
        lifecycle.addObserver(this)
        model?.getStateObservable()?.observe(this, this)
        model?.getEffectObservable()?.observe(this) { action -> action.visit(this as VIEW) }
    }

    protected abstract fun createModel(): VIEW_MODEL

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        model?.onStateChanged(event)

        if (lifecycle.currentState <= Lifecycle.State.DESTROYED) {
            lifecycle.removeObserver(this)
            // order matters
            model?.getEffectObservable()?.removeObservers(this)
            model?.getStateObservable()?.removeObservers(this)
        }
    }

    override fun onChanged(screenState: STATE) {
        screenState.visit(this as VIEW)
    }

    protected fun setModel(model: VIEW_MODEL) {
        this.model = model
    }
}