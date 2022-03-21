package com.velvet.models.cache

import com.velvet.models.card.Card
import kotlinx.coroutines.channels.Channel

class CacheImpl : Cache {
    private val cachedCards: ArrayList<Card> = ArrayList()
    private val cachedSelectedCard: Channel<Card> = Channel(capacity = 1)
    private val updatedChannel: Channel<Boolean> = Channel(capacity = 1)
    private var savedScrollPosition: Int = 0
    private var savedScrollOffset: Int = 0

    override suspend fun uploadCards(cards: List<Card>) {
        cachedCards.addAll(cards)
        updatedChannel.send(true)
    }

    override suspend fun retrieveCards(): List<Card> {
        return cachedCards
    }

    override suspend fun retrieveCardsAfterCall(): List<Card> {
        updatedChannel.receive()
        return cachedCards
    }

    override suspend fun uploadSelectedCard(card: Card) {
        cachedSelectedCard.send(card)
    }

    override suspend fun retrieveSelectedCard(): Card {
        return cachedSelectedCard.receive()
    }

    override fun isCacheEmpty(): Boolean {
        return cachedCards.isEmpty()
    }

    override fun getSavedPosition(): Int {
        return savedScrollPosition
    }

    override fun getSavedOffset(): Int {
        return savedScrollOffset
    }

    override fun savePosition(position: Int) {
        savedScrollPosition = position
    }

    override fun savedOffset(offset: Int) {
        savedScrollOffset = offset
    }
}