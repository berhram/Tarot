package com.velvet.models.cache

import com.velvet.models.card.Card

interface Cache {
    suspend fun uploadCards(cards: List<Card>)
    suspend fun retrieveCards() : List<Card>
    suspend fun uploadSelectedCard(card: Card)
    suspend fun retrieveSelectedCard() : Card
    suspend fun retrieveCardsAfterCall(): List<Card>
    fun isCacheEmpty() : Boolean
    fun getSavedPosition() : Int
    fun getSavedOffset() : Int
    fun savePosition(position: Int)
    fun savedOffset(offset: Int)
}