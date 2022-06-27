package com.velvet.tarot.card

import com.velvet.data.card.Card

data class CardState (
    val card: Card? = null,
    val isLoading: Boolean = true
)