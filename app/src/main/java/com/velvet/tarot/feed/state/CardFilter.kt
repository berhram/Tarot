package com.velvet.tarot.feed.state

data class CardFilter(val isMinorEnabled: Boolean, val isMajorEnabled: Boolean) {
    fun isEnable() : Boolean {
        return !isMajorEnabled || !isMinorEnabled
    }
}
