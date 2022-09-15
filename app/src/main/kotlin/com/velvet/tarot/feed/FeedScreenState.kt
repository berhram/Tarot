package com.velvet.tarot.feed

import androidx.compose.runtime.Immutable

@Immutable
data class FeedScreenState(
    val cards: CardFeedList = CardFeedList(),
    val message: Message = Message.LOADING,
    val searchText: String = "",
    val isSearchExpanded: Boolean = false,
    val isSimpleList: Boolean = false
) {
    enum class Message {
        NONE,
        LOADING,
        IS_NO_INTERNET,
        IS_SERVICE_UNAVAILABLE
    }
}