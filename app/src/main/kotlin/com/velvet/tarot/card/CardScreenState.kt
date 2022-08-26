package com.velvet.tarot.card

import androidx.compose.runtime.Immutable

@Immutable
data class CardScreenState(
    val cardDetails: CardDetails = CardDetails(),
    val isLoading: Boolean = true,
    val isNoInternetConnection: Boolean = false,
    val isServiceUnavailable: Boolean = false,
    val isNoSuchCard: Boolean = false
)