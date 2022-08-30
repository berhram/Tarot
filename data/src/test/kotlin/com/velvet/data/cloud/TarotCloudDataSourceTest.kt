package com.velvet.data.cloud

import com.velvet.core.exception.HandleError
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.RemoteCards
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class TarotCloudDataSourceTest {

    private val foundCards = listOf(
        Card("0", "The Death", "Major", "Nice", "Not nice", "A saddest one card"),
        Card("3", "The Life", "Major", "Hehe", "Not hehe", "A hehest one card")
    )

    private val allCards = listOf(
        Card("0", "The Death", "Major", "Nice", "Not nice", "A saddest one card"),
        Card("1", "The Moon", "Major", "Hot", "Cold", "A hottest one card"),
        Card("2", "The Sun", "Major", "Cold", "Hot", "A coldest one card"),
        Card("3", "The Life", "Major", "Hehe", "Not hehe", "A hehest one card")
    )

    @Test
    fun `success cards by keyword`() = runBlocking {
        val tarotCloudDataSource =
            TarotCloudDataSource.Base(TestService(foundCards, allCards), TestHandleError())
        Assert.assertEquals(foundCards, tarotCloudDataSource.cards("someKeyword"))
    }

    @Test
    fun `failed cards by keyword`() = runBlocking {
        val tarotCloudDataSource =
            TarotCloudDataSource.Base(TestService(foundCards, allCards), TestHandleError())
        Assert.assertEquals(emptyList<Card>(), tarotCloudDataSource.cards("abrakadabra"))
    }

    @Test
    fun `success all cards`() = runBlocking {
        val tarotCloudDataSource =
            TarotCloudDataSource.Base(TestService(foundCards, allCards), TestHandleError())
        Assert.assertEquals(allCards, tarotCloudDataSource.cards())
    }

    private class TestHandleError : HandleError {

        override fun handle(error: Exception): Exception = error
    }

    private class TestService(
        private val foundCards: List<Card>,
        private val allCards: List<Card>
    ) : TarotService {

        override suspend fun searchByKeyword(keyword: String): RemoteCards =
            RemoteCards(if (keyword == "someKeyword") foundCards else emptyList())

        override suspend fun allCards(): RemoteCards = RemoteCards(allCards)
    }
}