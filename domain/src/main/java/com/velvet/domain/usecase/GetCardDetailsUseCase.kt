package com.velvet.domain.usecase

import com.velvet.data.card.Card
import com.velvet.data.repo.Repository

class GetCardDetailsUseCase(private val repository: Repository) {
    suspend operator fun invoke(cardName: String): Card {
        return repository.getCard(cardName = cardName)
    }
}
