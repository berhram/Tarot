package com.velvet.data.card

data class CardFilter(
    val isMinorEnabled: Boolean = true,
    val isMajorEnabled: Boolean = true
) {

    fun isEnable(): Boolean {
        return !isMajorEnabled || !isMinorEnabled
    }

    fun filterCard(card: Card): Boolean {
        return when (card.type) {
            CardTypes.MINOR -> isMajorEnabled
            CardTypes.MAJOR -> isMinorEnabled
        }
    }
}
