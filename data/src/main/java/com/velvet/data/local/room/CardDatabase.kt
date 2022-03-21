package com.velvet.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.velvet.data.card.Card

@Database(entities = [Card::class], version = 1)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    companion object {
        const val DB_NAME = "app-card-database"
    }
}
