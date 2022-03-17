package com.velvet.tarot.fate

import com.velvet.models.card.CardDetailsScheme

data class FateScreenState (
    val layoutType: GuessingTypes = GuessingTypes.ONE,
    val cards: List<CardDetailsScheme> = mapOf(pairs = arrayOf(Pair(GuessingTypes.ONE, emptyList()), Pair(GuessingTypes.THREE, emptyList()))),
    val isAnimated: Boolean = false,
    val isInitial: Boolean = true
)