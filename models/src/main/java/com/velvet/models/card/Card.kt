package com.velvet.models.card;

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.velvet.models.Strings

@Entity
data class Card (
    @PrimaryKey val time: Long,
    val name: String,
    val art: String,
    val type: String,
    val meaning: String,
    val description: String,
) {
    companion object {
        fun initial() = Card(
                time = System.currentTimeMillis(),
                name = "?????",
                art = Strings.Blank,
                type = "?????",
                meaning = "?????",
            description = "?????"
        )

    }
}
