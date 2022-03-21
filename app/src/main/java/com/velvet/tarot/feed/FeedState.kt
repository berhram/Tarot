package com.velvet.tarot.feed

import com.velvet.models.card.Card

data class FeedState
    (val isLoading: Boolean,
     val cards: List<Card>,
     val isInitial: Boolean,
     val scrollPosition: Int,
     val scrollOffset: Int,
     val isScrollNeeded: Boolean)