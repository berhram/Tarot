package com.velvet.domain.usecases

import com.velvet.domain.CardDomain

interface CachedCardsUseCase {

    suspend fun cachedCards(): List<CardDomain>
}