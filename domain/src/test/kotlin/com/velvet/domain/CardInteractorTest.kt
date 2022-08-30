package com.velvet.domain

import com.velvet.data.Repository
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt
import com.velvet.domain.mappers.FromCardArtToString
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class CardInteractorTest {

    private val cards = listOf(
        Card("0", "The King", "major", "nice", "hehe", "the king of something"),
        Card("1", "The Queen", "major", "not hehe", "nice", "the queen of something")
    )

    private val domainCards = listOf(
        CardDomain("0", "The King", "major", "nice", "hehe", "the king of something", "ART"),
        CardDomain("1", "The Queen", "major", "not hehe", "nice", "the queen of something", "ART")
    )

    @Test
    fun `success cached cards`() = runBlocking {
        val repository = TestRepository(cards)
        val interactor = CardInteractor.Base(repository, FromCardArtToString())
        interactor.cachedCards()
        Assert.assertEquals(domainCards, interactor.cachedCards())
    }

    @Test
    fun `success cards`() = runBlocking {
        val repository = TestRepository(cards)
        val interactor = CardInteractor.Base(repository, FromCardArtToString())
        Assert.assertEquals(domainCards, interactor.cards())
    }


    @Test
    fun `success cards by keyword`() = runBlocking {
        val repository = TestRepository(cards)
        val interactor = CardInteractor.Base(repository, FromCardArtToString())
        Assert.assertEquals(
            listOf(
                CardDomain(
                    "0",
                    "The King",
                    "major",
                    "nice",
                    "hehe",
                    "the king of something",
                    "ART"
                )
            ), interactor.cards("smth")
        )
    }

    @Test
    fun `success card with real art`() = runBlocking {
        val repository = TestRepository()
        val interactor = CardInteractor.Base(repository, FromCardArtToString())
        Assert.assertEquals(
            CardDomain(
                "2",
                "The Slime",
                "minor",
                "bubble",
                "wouble",
                "slimee",
                "ART"
            ),
            interactor.cardById("2")
        )
    }

    @Test
    fun `success card with default art`() = runBlocking {
        val repository = TestRepository(isRealArt = false)
        val interactor = CardInteractor.Base(repository, FromCardArtToString())
        Assert.assertEquals(
            CardDomain(
                "2",
                "The Slime",
                "minor",
                "bubble",
                "wouble",
                "slimee",
                "MMM"
            ),
            interactor.cardById("2")
        )
    }

    private class TestRepository(
        private val cachedCards: List<Card> = emptyList(),
        private val isRealArt: Boolean = true
    ) : Repository {

        override suspend fun cachedCards(): List<Card> = cachedCards

        override suspend fun art(name: String): CardArt {
            if (isRealArt)
                return CardArt("Mona Lisa", "ART")
            return CardArt()
        }

        override suspend fun card(id: String): Card = Card(
            "2",
            "The Slime",
            "minor",
            "bubble",
            "wouble",
            "slimee"
        )

        override suspend fun cards(): List<Card> = cachedCards

        override suspend fun cards(keyword: String): List<Card> = listOf(cachedCards.first())

        override suspend fun defaultArt(): CardArt = CardArt("Black Square", "MMM")
    }
}