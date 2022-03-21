package com.velvet.tarot.card

import com.velvet.data.card.Card

data class CardState (
    val card: Card,
    val isLoading: Boolean
)