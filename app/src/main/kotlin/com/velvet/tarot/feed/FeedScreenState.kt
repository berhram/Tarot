package com.velvet.tarot.feed

data class FeedScreenState(
    val cards: List<CardItem> = emptyList(),
    val isLoading: Boolean = true,
    val searchText: String = "",
) {

}