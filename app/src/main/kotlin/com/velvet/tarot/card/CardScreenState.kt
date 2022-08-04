package com.velvet.tarot.card

import com.velvet.data.schemas.Card

data class CardScreenState(
    val cardDetails: Card = Card(),
    val art: String = "",
    val isLoading: Boolean = true,
    val isNoInternetConnection: Boolean = false,
    val isServiceUnavailable: Boolean = false
)