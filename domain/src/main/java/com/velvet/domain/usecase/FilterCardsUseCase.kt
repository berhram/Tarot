package com.velvet.domain.usecase

import com.velvet.data.card.Card
import com.velvet.data.card.CardTypes
import com.velvet.data.repo.Repository

class FilterCardsUseCase(private val repository: Repository) {
    suspend operator fun invoke(isMajorEnabled: Boolean, isMinorEnabled: Boolean): List<Card> {
         return repository.getCards().filter {
            isMajorEnabled && it.type == CardTypes.MAJOR || isMinorEnabled && it.type == CardTypes.MINOR
         }
    }
}