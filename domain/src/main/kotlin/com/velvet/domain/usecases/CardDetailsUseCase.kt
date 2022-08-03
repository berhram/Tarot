package com.velvet.domain.usecases

import com.velvet.data.schemas.Card

interface CardDetailsUseCase {

    suspend fun cardById(id: String): Card
}