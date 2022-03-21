package com.velvet.domain.usecase

import com.velvet.data.card.Card
import com.velvet.data.repo.Repository

class GetAllCardsUseCase(private val repository:Repository) {
    suspend operator fun invoke(): List<Card> {
        return repository.getCards()
    }
}
