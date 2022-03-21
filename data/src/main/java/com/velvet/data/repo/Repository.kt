package com.velvet.data.repo

import com.velvet.data.card.Card

interface Repository {
    suspend fun getCards() : List<Card>
    suspend fun getCard(cardName: String) : Card
    suspend fun fetch()
}