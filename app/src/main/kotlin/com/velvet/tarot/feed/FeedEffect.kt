package com.velvet.tarot.feed

import com.velvet.coremvi.Effect

sealed class FeedEffect : Effect {
    data class ShowCard(val cardName: String) : FeedEffect()
    object ErrorRefresh : FeedEffect()
}