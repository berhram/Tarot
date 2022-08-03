package com.velvet.domain.usecases

import com.velvet.data.schemas.Card

interface CardsByKeywordUseCase {

    suspend fun cards(keyword: String): List<Card>
}