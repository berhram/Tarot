package com.velvet.tarot.card

import com.velvet.tarot.ui.CardDetailsState

data class CardScreenState(
    val card: CardDetailsState? = null,
    val isLoading: Boolean = true
)