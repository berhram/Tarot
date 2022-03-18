package com.velvet.models.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.velvet.models.card.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card ORDER BY time DESC LIMIT 1")
    fun getLast(): Card

    @Insert
    fun insert(card: Card)

    @Delete
    fun delete(user: Card)
}