package com.velvet.tarot.feed

import com.velvet.domain.CardFilter
import com.velvet.tarot.ui.CardListState
import com.velvet.tarot.ui.SearchBarState

data class FeedState(
    val isLoading: Boolean = false,
    val cards: List<CardListState> = emptyList(),
    val searchBarState: SearchBarState = SearchBarState(),
    val filter: CardFilter = CardFilter.Base(),
    val isExpanded: Boolean = false
)