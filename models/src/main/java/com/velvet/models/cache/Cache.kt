package com.velvet.models.cache

import com.velvet.models.card.Card

interface Cache {
    suspend fun uploadCards(cards: List<Card>)
    suspend fun retrieveCards() : List<Card>
    suspend fun uploadSelectedCard(card: Card)
    suspend fun retrieveSelectedCard() : Card
    fun isCacheEmpty() : Boolean
    suspend fun retrieveCardsAfterCall(): List<Card>
    fun getSavedPosition() : Int
    fun getSavedOffset() : Int
    fun savePosition(position: Int)
    fun savedOffset(offset: Int)
}