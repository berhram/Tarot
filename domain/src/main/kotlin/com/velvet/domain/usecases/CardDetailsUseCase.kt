package com.velvet.domain.usecases

import com.velvet.domain.CardDomain

interface CardDetailsUseCase {

    suspend fun cardById(id: String): CardDomain
}