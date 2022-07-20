package com.velvet.data.cache

import com.velvet.data.repo.Status

interface CacheRepository {
    suspend fun sendStatus(status: Status)
    suspend fun sendCard(card: Card)
    suspend fun sendCards(cards: List<Card>)
}