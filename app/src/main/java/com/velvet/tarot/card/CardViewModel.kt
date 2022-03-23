package com.velvet.tarot.card

import androidx.lifecycle.ViewModel
import com.velvet.domain.usecase.GetCardDetailsUseCase
import com.velvet.data.card.Card
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CardViewModel(private val getCardDetailsUse: GetCardDetailsUseCase, private val cardName: String) : ContainerHost<CardState, CardEffect>, ViewModel() {

    override val container = container<CardState, CardEffect>(CardState(card = Card.initial(), isLoading = true))

    init {
        intent {
            val card = getCardDetailsUse.invoke(cardName = cardName)
            reduce {
                state.copy(card = card, isLoading = false)
            }
        }
    }

    fun goBack() = intent {
        postSideEffect(CardEffect.GoBack)
    }
}