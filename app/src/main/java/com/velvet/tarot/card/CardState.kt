package com.velvet.tarot.card

import com.velvet.models.card.Card

data class CardState (
    val card: Card,
    val isLoading: Boolean
)