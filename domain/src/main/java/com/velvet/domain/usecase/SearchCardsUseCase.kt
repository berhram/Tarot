package com.velvet.domain.usecase

import com.velvet.data.card.Card
import com.velvet.data.repo.Repository

class SearchCardsUseCase(private val repository: Repository) {
    suspend operator fun invoke(keyword: String): List<Card> {
        return if (keyword.isEmpty()) {
            repository.getCards()
        } else {
            repository.getCards().filter { it.name.contains(keyword, ignoreCase = true) }
        }
    }
}