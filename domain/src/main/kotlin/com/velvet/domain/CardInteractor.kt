package com.velvet.domain

import com.velvet.domain.usecases.CardsTitlesByNameUseCase
import com.velvet.domain.usecases.SpecificCardUseCase

interface CardInteractor : SpecificCardUseCase, CardsTitlesByNameUseCase