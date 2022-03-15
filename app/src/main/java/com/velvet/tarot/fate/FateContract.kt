package com.velvet.tarot.fate

import com.velvet.models.card.CardDetails
import com.velvet.mvi.ScreenEffect
import com.velvet.mvi.ScreenEvent
import com.velvet.mvi.ScreenState

class FateContract {
    sealed class Event : ScreenEvent {
        object OnOneCardButtonClicked : Event()
        object OnThreeCardButtonClicked : Event()
    }

    data class State(
        val fateState: FateState
    ) : ScreenState

    sealed class FateState {
        object Idle : FateState()
        object Loading : FateState()
        data class Success(val type: GuessingTypes, val cards: List<CardDetails>) : FateState()
    }

    sealed class Effect : ScreenEffect {
        object ShowToast : Effect()
    }
}