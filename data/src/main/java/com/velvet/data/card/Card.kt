package com.velvet.data.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card (
    @PrimaryKey val name: String,
    val art: String,
    val type: CardTypes,
    val meaningUp: String,
    val meaningRev: String,
    val description: String,
) {
    companion object {
        fun initial() = Card(
                name = "",
                art = "",
                type = CardTypes.NONE,
                meaningUp = "",
                meaningRev = "",
            description = ""
        )
    }
}
