package com.velvet.data.cache

import android.util.Log
import com.velvet.data.card.Card
import com.velvet.data.repo.Status
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class CacheImpl : CacheClient, CacheRepository {
    private val statusChannel: Channel<Status> = Channel(1)
    private val cardChannel: Channel<Card> = Channel(1)
    private val cardsChannel: Channel<List<Card>> = Channel(1)

    override suspend fun sendStatus(status: Status) {
        statusChannel.send(status)
    }

    override suspend fun sendCard(card: Card) {
        cardChannel.send(card)
    }

    override suspend fun sendCards(cards: List<Card>) {
        Log.d("CARDS", "sendCards: $this")
        cardsChannel.send(cards)
    }

    override fun getStatusChannel(): ReceiveChannel<Status> {
        return statusChannel
    }

    override fun getCardsChannel(): ReceiveChannel<List<Card>> {
        Log.d("CARDS", "getCards: $this")
        return cardsChannel
    }

    override fun getCardChannel(): ReceiveChannel<Card> {
        return cardChannel
    }
}