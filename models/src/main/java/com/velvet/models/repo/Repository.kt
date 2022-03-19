package com.velvet.models.repo

import com.velvet.models.card.Card

interface Repository {
    suspend fun getCards() : List<Card>
    suspend fun getCard(cardName: String) : Card
}