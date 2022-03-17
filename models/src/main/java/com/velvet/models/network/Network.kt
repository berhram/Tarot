package com.velvet.models.network

import com.velvet.models.card.CardDetailsScheme

interface Network {
    suspend fun getCards(number: Int) : List<CardDetailsScheme>
}