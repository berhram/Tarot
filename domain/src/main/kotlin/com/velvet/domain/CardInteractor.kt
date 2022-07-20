package com.velvet.domain

import com.velvet.domain.usecases.CardArtUseCase
import com.velvet.domain.usecases.CardItemsUseCase
import com.velvet.domain.usecases.CardDetailsUseCase

interface CardInteractor : CardDetailsUseCase, CardItemsUseCase, CardArtUseCase