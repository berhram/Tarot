package com.velvet.data.local.room

import androidx.room.*
import com.velvet.data.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface CardDao {
    @Query("SELECT * FROM card WHERE name LIKE :cardName LIMIT 1")
    fun findByName(cardName: String): Card

    @Query("SELECT * FROM card")
    fun getAll(): Flow<List<Card>>

    fun getAllDistinctUntilChanged() = getAll().distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cards: List<Card>)

    @Delete
    fun deleteAll(cards: List<Card>)
}