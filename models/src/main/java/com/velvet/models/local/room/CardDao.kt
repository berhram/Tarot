package com.velvet.models.local.room

import androidx.room.*
import com.velvet.models.card.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card WHERE name LIKE :cardName LIMIT 1")
    fun findByName(cardName: String): Card

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cards: List<Card>)

    @Delete
    fun deleteAll(cards: List<Card>)
}