package com.velvet.tarot.feed

import com.velvet.domain.states.CardItem

data class FeedScreenState(
    val cards: List<CardItem> = emptyList(),
    val isLoading: Boolean = true,
    val searchText: String = "",
) {

}