package com.velvet.domain.usecase

import com.velvet.data.Strings
import com.velvet.data.card.Card
import com.velvet.data.repo.Repository

class FilterCardsUseCase(private val repository: Repository) {
    suspend operator fun invoke(isMajor: Boolean, isMinor: Boolean): List<Card> {
        return repository.getCards().filter {
            (it.type == Strings.Major && isMajor) || (it.type == Strings.Minor && isMinor)
        }
    }
}