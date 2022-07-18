package com.velvet.data.network

import com.velvet.data.card.schemas.CardScheme

interface Network {

    suspend fun getCards(): Result<List<CardScheme>>
}