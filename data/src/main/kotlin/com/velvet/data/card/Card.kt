package com.velvet.data.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    @PrimaryKey val name: String?,
    val art: String?,
    val arcana: String?,
    val meaningUp: String?,
    val meaningRev: String?,
    val description: String?
)
