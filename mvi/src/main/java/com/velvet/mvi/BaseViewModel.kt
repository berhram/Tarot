package com.velvet.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

//responsible for fetching data from data source and handling events from ui layout to transform them in intentions for reducer
abstract class BaseViewModel<State : com.velvet.mvi.ScreenState, Event : ScreenEvent> : ViewModel() {

    abstract val state: Flow<State>
}