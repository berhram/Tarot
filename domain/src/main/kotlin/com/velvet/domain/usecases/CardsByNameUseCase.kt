package com.velvet.domain.usecases

import com.velvet.domain.states.CardItemState

interface CardsByNameUseCase {

    fun cardsByName(name: String): List<CardItemState>
}