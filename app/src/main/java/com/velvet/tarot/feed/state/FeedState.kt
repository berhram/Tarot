package com.velvet.tarot.feed.state

import com.velvet.data.card.Card

data class FeedState
    (val isLoading: Boolean,
     val cards: List<Card>,
     val searchText: String,
     val filter: CardFilter,
     val isExpanded: Boolean)