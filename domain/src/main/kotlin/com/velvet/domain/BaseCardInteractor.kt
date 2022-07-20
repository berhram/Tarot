package com.velvet.domain

import com.velvet.core.Mapper
import com.velvet.data.Card
import com.velvet.domain.states.CardDetailsState
import com.velvet.domain.states.CardItemState

class BaseCardInteractor(
    private val cardItemStateMapper : Mapper<Card, CardItemState>,
    private val cardDetailsState
) : CardInteractor {

    override fun cardsByName(name: String): List<CardItemState> {
        TODO("Not yet implemented")
    }

    override fun cardById(id: String): CardDetailsState {
        TODO("Not yet implemented")
    }
}