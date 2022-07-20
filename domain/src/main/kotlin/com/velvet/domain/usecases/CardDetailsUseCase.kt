package com.velvet.domain.usecases

import com.velvet.domain.states.CardDetails

interface CardDetailsUseCase {

    suspend fun cardById(id: String): CardDetails
}