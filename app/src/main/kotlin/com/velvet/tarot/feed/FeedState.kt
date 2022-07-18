package com.velvet.tarot.feed

import com.velvet.domain.states.SearchBarState
import com.velvet.tarot.ui.CardListState

data class FeedState(
    val cards: CardListState = CardListState(),
    val searchBarState: SearchBarState = SearchBarState(),
)