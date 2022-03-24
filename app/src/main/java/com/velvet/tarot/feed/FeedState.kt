package com.velvet.tarot.feed

import com.velvet.data.card.Card
import com.velvet.data.card.CardFilter

data class FeedState
    (val isLoading: Boolean,
     val cards: List<Card>,
     val searchText: String,
     val filter: CardFilter,
     val isExpanded: Boolean)