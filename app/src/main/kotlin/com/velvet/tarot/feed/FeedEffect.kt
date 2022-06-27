package com.velvet.tarot.feed

sealed class FeedEffect {
    data class ShowCard(val cardName: String) : FeedEffect()
    object ErrorRefresh : FeedEffect()
}