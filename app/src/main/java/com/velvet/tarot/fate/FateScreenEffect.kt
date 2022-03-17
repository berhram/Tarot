package com.velvet.tarot.fate

import com.velvet.models.card.CardDetailsScheme

sealed class FateScreenEffect {
    data class OnOneCardButtonClicked(val cards: Pair<GuessingTypes, List<CardDetailsScheme>>) : FateScreenEffect()
    data class OnThreeCardButtonClicked(val cards: Pair<GuessingTypes, List<CardDetailsScheme>>) : FateScreenEffect()
}