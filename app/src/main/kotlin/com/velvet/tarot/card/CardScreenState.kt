package com.velvet.tarot.card


data class CardScreenState(
    val cardDetails: CardDetails = CardDetails(),
    val isLoading: Boolean = true,
    val isNoInternetConnection: Boolean = false,
    val isServiceUnavailable: Boolean = false,
    val isNoSuchCard: Boolean = false
)