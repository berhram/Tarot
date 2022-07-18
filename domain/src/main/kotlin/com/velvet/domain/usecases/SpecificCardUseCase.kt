package com.velvet.domain.usecases

import com.velvet.domain.CardUI

interface SpecificCardUseCase {

    fun cardById(id: String): CardUI
}