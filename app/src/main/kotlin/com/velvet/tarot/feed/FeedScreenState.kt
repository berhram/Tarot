package com.velvet.tarot.feed

import androidx.compose.runtime.Immutable

@Immutable
data class FeedScreenState(
    val cards: CardFeedList = CardFeedList(),
    val isLoading: Boolean = true,
    val isNoInternetConnection: Boolean = false,
    val isServiceUnavailable: Boolean = false,
    val searchText: String = "",
    val isSearchExpanded: Boolean = false,
    val simpleList: Boolean = false
)