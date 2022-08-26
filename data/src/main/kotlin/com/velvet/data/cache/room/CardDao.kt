package com.velvet.data.cache.room

import androidx.room.*
import com.velvet.data.schemas.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): Card

    @Query("SELECT EXISTS(SELECT * FROM card WHERE id = :id)")
    fun cardExists(id: String): Boolean

    @Query("SELECT * FROM card")
    fun getAll(): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cards: List<Card>)

    @Delete
    fun deleteAll(cards: List<Card>)
}