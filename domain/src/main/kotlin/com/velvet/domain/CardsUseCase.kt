package com.velvet.domain

import com.velvet.domain.states.CardItemState

interface CardsUseCase {

    fun cards(filter: CardFilter): List<CardItemState>

    class Base : CardsUseCase {

        override fun cards(filter: CardFilter): List<CardItemState> {
            TODO("Not yet implemented")
        }
    }
}