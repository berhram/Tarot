package com.velvet.models.network

import com.velvet.models.card.CardDetails

interface Network {
    suspend fun getCards(number: Int) : List<CardDetails>
}