package com.velvet.models.repo

interface Repository {
    suspend fun getCards()
    suspend fun getCard(cardName: String)
}