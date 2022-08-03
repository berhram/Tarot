package com.velvet.tarot.card

import com.velvet.domain.models.CardDetails

data class CardScreenState(
    val cardDetails: CardDetails = CardDetails(),
    val art: String = "",
    val isLoading: Boolean = true
)