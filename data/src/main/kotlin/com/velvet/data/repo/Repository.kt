package com.velvet.data.repo

interface Repository {
    suspend fun getCards()
    suspend fun getCard(cardName: String)
    suspend fun fetch()
}