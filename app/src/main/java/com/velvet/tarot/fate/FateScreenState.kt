package com.velvet.tarot.fate

import com.velvet.models.card.Card

data class FateScreenState (
    val card: Card,
    val isAnimated: Boolean
)