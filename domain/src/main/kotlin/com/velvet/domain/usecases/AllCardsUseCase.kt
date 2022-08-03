package com.velvet.domain.usecases

import com.velvet.data.schemas.Card

interface AllCardsUseCase {

    suspend fun cards(): List<Card>
}