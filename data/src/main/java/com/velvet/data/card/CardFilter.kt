package com.velvet.data.card

data class CardFilter(val isMinorEnabled: Boolean, val isMajorEnabled: Boolean) {
    fun isEnable() : Boolean {
        return !isMajorEnabled || !isMinorEnabled
    }
}
