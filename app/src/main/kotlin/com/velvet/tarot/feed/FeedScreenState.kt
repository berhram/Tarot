package com.velvet.tarot.feed


data class FeedScreenState(
    val cards: List<CardFeed> = emptyList(),
    val isLoading: Boolean = true,
    val isNoInternetConnection: Boolean = false,
    val isServiceUnavailable: Boolean = false,
    val searchText: String = "",
    val isSearchExpanded: Boolean = false
)