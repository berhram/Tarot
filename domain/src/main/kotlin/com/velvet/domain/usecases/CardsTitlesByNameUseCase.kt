package com.velvet.domain.usecases

import com.velvet.domain.states.CardItemState

interface CardsTitlesByNameUseCase {

    fun cardsByName(name: String): List<CardItemState>
}