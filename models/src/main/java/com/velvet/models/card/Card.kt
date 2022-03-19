package com.velvet.models.card;

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.velvet.models.Strings

@Entity
data class Card (
    @PrimaryKey val name: String,
    val art: String,
    val type: String,
    val meaningUp: String,
    val meaningRev: String,
    val description: String,
) {
    companion object {
        fun initial() = Card(
                name = "?????",
                art = Strings.Blank,
                type = "?????",
                meaningUp = "?????",
                meaningRev = "?????",
            description = "?????"
        )
    }
}
