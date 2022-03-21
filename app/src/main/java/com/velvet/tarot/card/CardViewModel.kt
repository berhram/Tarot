package com.velvet.tarot.card

import androidx.lifecycle.ViewModel
import com.velvet.models.cache.Cache
import com.velvet.models.card.Card
import com.velvet.models.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: Repository, private val cache: Cache) : ContainerHost<CardState, CardEffect>, ViewModel() {

    override val container = container<CardState, CardEffect>(CardState(card = Card.initial(), isLoading = true))

    fun getCard(cardName: String) = intent {
        repository.getCard(cardName)
        val card = cache.retrieveSelectedCard()
        reduce {
            state.copy(card = card, isLoading = false)
        }
    }
}