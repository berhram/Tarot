package com.velvet.data.cache

import com.velvet.data.card.Card
import com.velvet.data.repo.Status
import kotlinx.coroutines.channels.ReceiveChannel

interface CacheClient {
    fun getStatusChannel(): ReceiveChannel<Status>
    fun getCardsChannel(): ReceiveChannel<List<Card>>
    fun getCardChannel(): ReceiveChannel<Card>
}