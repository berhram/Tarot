package com.velvet.tarot.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.domain.usecases.SpecificCardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class CardViewModel(
    private val cardId: String,
    private val specificCardUseCase: SpecificCardUseCase
) : ContainerHost<CardScreenState, CardScreenEffect>, ViewModel() {
    override val container = container<CardScreenState, CardScreenEffect>(CardScreenState())

    init {
        intent {
            viewModelScope.launch(Dispatchers.IO) {
                specificCardUseCase.cardById(cardId)
            }
        }
    }

    fun goBack() = intent {
        postSideEffect(CardScreenEffect.GoBack)
    }
}