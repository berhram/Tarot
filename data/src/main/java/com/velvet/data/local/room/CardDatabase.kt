package com.velvet.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.velvet.data.card.Card

@Database(entities = [Card::class], version = 1)
@TypeConverters(Converters::class)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    companion object {
        const val DB_NAME = "app-card-database"
    }
}
