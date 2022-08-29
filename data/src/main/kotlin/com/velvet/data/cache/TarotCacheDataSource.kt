package com.velvet.data.cache

import com.velvet.data.exception.NoSuchCardException
import com.velvet.data.schemas.Card

interface TarotCacheDataSource {

    suspend fun cards(): List<Card>

    suspend fun save(cards: List<Card>)

    suspend fun card(id: String): Card

    class Base(private val dao: CardDao) : TarotCacheDataSource {

        override suspend fun cards(): List<Card> = dao.getAll()

        override suspend fun save(cards: List<Card>) {
            dao.deleteAll(dao.getAll())
            dao.insertAll(cards)
        }

        override suspend fun card(id: String): Card {
            if (dao.cardExists(id))
                return dao.findById(id)
            throw NoSuchCardException()
        }
    }
}