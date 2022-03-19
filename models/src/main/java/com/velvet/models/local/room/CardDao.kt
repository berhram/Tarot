package com.velvet.models.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.velvet.models.card.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card WHERE name LIKE :cardName LIMIT 1")
    fun findByName(cardName: String): Card

    @Insert
    fun insertAll(cards: List<Card>)

    @Delete
    fun deleteAll(cards: List<Card>)
}