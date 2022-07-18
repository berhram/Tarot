package com.velvet.tarot.card

import com.velvet.domain.states.CardDetailsState

data class CardScreenState(
    val card: CardDetailsState? = null,
    val isLoading: Boolean = true
)