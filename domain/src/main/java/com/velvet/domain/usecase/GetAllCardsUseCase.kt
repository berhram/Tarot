package com.velvet.domain.usecase;

import android.util.Log
import com.velvet.data.card.Card;
import com.velvet.data.repo.Repository;

class GetAllCardsUseCase(private val repository:Repository) {
    suspend operator fun invoke(): List<Card> {
        Log.d("CARDS", "get all cards invoked")
        return repository.getCards()
    }
}
