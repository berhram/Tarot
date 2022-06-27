package com.velvet.tarot.feed

import com.velvet.data.card.Card
import com.velvet.data.card.CardFilter

data class FeedState
    (val isLoading: Boolean = false,
     val cards: List<Card> = emptyList(),
     val searchText: String = "",
     val filter: CardFilter = CardFilter(),
     val isExpanded: Boolean = false)