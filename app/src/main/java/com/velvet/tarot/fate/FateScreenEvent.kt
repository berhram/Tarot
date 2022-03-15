package com.velvet.tarot.fate

import androidx.compose.runtime.Immutable
import com.velvet.models.card.CardDetails
import com.velvet.mvi.ScreenEvent

@Immutable
sealed class FateScreenEvent : ScreenEvent {
    object Initial : FateScreenEvent()
    data class OnOneCardButtonClicked(val cards: List<CardDetails>) : FateScreenEvent()
    data class OnThreeCardButtonClicked(val cards: List<CardDetails>) : FateScreenEvent()
}