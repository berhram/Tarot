package com.velvet.data.network

import com.velvet.data.card.CardDetailsScheme

interface Network {
    suspend fun getCards() : Result<List<CardDetailsScheme>>
}