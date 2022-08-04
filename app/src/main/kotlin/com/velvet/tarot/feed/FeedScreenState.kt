package com.velvet.tarot.feed

import com.velvet.data.schemas.Card

data class FeedScreenState(
    val cards: List<Card> = emptyList(),
    val isLoading: Boolean = true,
    val isNoInternetConnection: Boolean = false,
    val isServiceUnavailable: Boolean = false,
    val searchText: String = ""
)