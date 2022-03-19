package com.velvet.tarot.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.models.card.Card
import com.velvet.models.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: Repository) : ContainerHost<CardState, CardEffect>, ViewModel() {

    override val container = container<CardState, CardEffect>(CardState(card = Card.initial(), isLoading = true))

    fun getCard(cardName: String) = intent {
        val card = repository.getCard(cardName)
        reduce {
            state.copy(card = card, isLoading = false)
        }
    }
}