package com.velvet.data.schemas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    @PrimaryKey
    val nameShort: String,
    val name: String,
    val type: String,
    val meaningUp: String,
    val meaningRev: String,
    val description: String
)