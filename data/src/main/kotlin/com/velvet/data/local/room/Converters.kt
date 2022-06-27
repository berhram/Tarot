package com.velvet.data.local.room

import androidx.room.TypeConverter
import com.velvet.data.card.CardTypes

class Converters {

    @TypeConverter
    fun toCardType(value: String) = enumValueOf<CardTypes>(value)

    @TypeConverter
    fun fromHealth(value: CardTypes) = value.name
}