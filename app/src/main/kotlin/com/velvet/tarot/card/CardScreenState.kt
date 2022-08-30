package com.velvet.tarot.card

import androidx.compose.runtime.Immutable

@Immutable
data class CardScreenState(
    val cardDetails: CardDetails = CardDetails(),
    val isNoSuchCard: Boolean = false
)