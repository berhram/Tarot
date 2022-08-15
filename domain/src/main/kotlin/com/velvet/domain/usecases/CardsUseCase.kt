package com.velvet.domain.usecases

import com.velvet.domain.CardDomain

interface CardsUseCase {

    suspend fun cards(): List<CardDomain>
}