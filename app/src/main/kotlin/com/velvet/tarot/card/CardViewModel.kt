package com.velvet.tarot.card

import com.velvet.core.viewmodel.ReactiveViewModel
import com.velvet.data.exception.NoSuchCardException
import com.velvet.domain.usecases.CardDetailsUseCase
import kotlinx.coroutines.Dispatchers
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CardViewModel(
    private val cardId: String,
    private val cardDetailsUseCase: CardDetailsUseCase,
) : ReactiveViewModel<CardScreenState, CardScreenEffect>() {

    override val container = container<CardScreenState, CardScreenEffect>(
        CardScreenState(),
        Container.Settings(intentDispatcher = Dispatchers.IO)
    )

    init {
        details()
    }

    private fun details() = intent {
        intercept { cardDetailsUseCase.cardById(cardId) }.map { cardDomain ->
            reduce {
                state.copy(
                    cardDetails = CardDetails.fromCardDomain(cardDomain)
                )
            }
        }
    }

    fun goBack() = intent {
        postSideEffect(CardScreenEffect.GoBack)
    }

    override suspend fun interceptError(error: Exception) {
        when (error) {
            is NoSuchCardException -> intent { reduce { state.copy(isNoSuchCard = true) } }
        }
    }
}