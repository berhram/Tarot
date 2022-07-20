package com.velvet.domain.usecases

import com.velvet.domain.states.CardDetailsState

interface SpecificCardUseCase {

    fun cardById(id: String): CardDetailsState
}