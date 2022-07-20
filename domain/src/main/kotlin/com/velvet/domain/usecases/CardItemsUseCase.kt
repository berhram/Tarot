package com.velvet.domain.usecases

import com.velvet.domain.states.CardItem

interface CardItemsUseCase {

    suspend fun cards(keyword: String): List<CardItem>
}