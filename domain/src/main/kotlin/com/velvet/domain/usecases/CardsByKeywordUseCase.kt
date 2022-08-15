package com.velvet.domain.usecases

import com.velvet.domain.CardDomain

interface CardsByKeywordUseCase {

    suspend fun cards(keyword: String): List<CardDomain>
}