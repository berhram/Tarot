package com.velvet.tarot.fate

import androidx.compose.runtime.Immutable
import com.velvet.models.card.CardDetails
import com.velvet.mvi.ScreenState

@Immutable
data class FateScreenState (
    val isLoading: Boolean,
    val error: Throwable?,
    val data: List<CardDetails>
) : ScreenState {
    companion object {
        fun initial() = FateScreenState(
            isLoading = false,
            data = emptyList(),
            error = null
        )
    }
}