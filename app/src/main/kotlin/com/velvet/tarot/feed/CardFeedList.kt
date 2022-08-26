package com.velvet.tarot.feed

import androidx.compose.runtime.Immutable

@Immutable
data class CardFeedList(
    val list: List<CardFeed> = listOf()
)