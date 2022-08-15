package com.velvet.data.cache

import com.velvet.data.cache.room.CardDao
import com.velvet.data.exception.NoSuchCardException
import com.velvet.data.schemas.Card
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class TarotCacheDataSourceTest {

    private val allCards = listOf(
        Card("0", "The Death", "Major", "Nice", "Not nice", "A saddest one card"),
        Card("1", "The Moon", "Major", "Hot", "Cold", "A hottest one card"),
        Card("2", "The Sun", "Major", "Cold", "Hot", "A coldest one card"),
        Card("3", "The Life", "Major", "Hehe", "Not hehe", "A hehest one card")
    )

    @Test
    fun `success get card by id`() = runBlocking {
        val dao = TestDao(allCards)
        val tarotCacheDataSource = TarotCacheDataSource.Base(dao)
        Assert.assertEquals(
            Card("0", "The Death", "Major", "Nice", "Not nice", "A saddest one card"),
            tarotCacheDataSource.card("0")
        )
    }

    @Test
    fun `failed get card by id`(): Unit = runBlocking {
        val dao = TestDao(allCards)
        val tarotCacheDataSource = TarotCacheDataSource.Base(dao)
        Assert.assertThrows(NoSuchCardException()::class.java) {
            runBlocking { tarotCacheDataSource.card("100") }
        }
    }

    @Test
    fun `success get all cards`() = runBlocking {
        val dao = TestDao(allCards)
        val tarotCacheDataSource = TarotCacheDataSource.Base(dao)
        Assert.assertEquals(allCards, tarotCacheDataSource.cards())
    }

    @Test
    fun `success save cards`() = runBlocking {
        val dao = TestDao()
        val tarotCacheDataSource = TarotCacheDataSource.Base(dao)
        tarotCacheDataSource.save(allCards)
        Assert.assertEquals(allCards, dao.storage)
    }

    private class TestDao(initStorage: List<Card> = emptyList()) : CardDao {

        val storage = mutableListOf<Card>()

        init {
            storage.addAll(initStorage)
        }

        override fun findById(id: String): Card {
            val card = storage.find { it.id == id }
            if (card != null)
                return card
            return Card()
        }

        override fun cardExists(id: String): Boolean = id == "0" || id == "1" || id == "2" || id == "3"

        override fun getAll(): List<Card> = storage

        override fun insertAll(cards: List<Card>) {
            storage.addAll(cards)
        }

        override fun deleteAll(cards: List<Card>) {
            storage.removeAll(cards)
        }
    }
}