package com.velvet.tarot.feed

import com.velvet.coremvi.State
import com.velvet.data.card.Card
import com.velvet.data.card.CardFilter
import com.velvet.tarot.ui.SearchBarState

data class FeedState(
    val isLoading: Boolean = false,
    val cards: List<Card> = emptyList(),
    val searchBarState: SearchBarState = SearchBarState(),
    val filter: CardFilter = CardFilter(),
    val isExpanded: Boolean = false
) : State