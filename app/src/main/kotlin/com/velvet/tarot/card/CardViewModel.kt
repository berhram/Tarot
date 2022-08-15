package com.velvet.tarot.card

import com.velvet.core.ReactiveViewModel
import com.velvet.core.exception.NoInternetConnectionException
import com.velvet.core.exception.ServiceUnavailableException
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
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(isLoading = true, isNoInternetConnection = false, isServiceUnavailable = false) }
        intercept { cardDetailsUseCase.cardById(cardId) }.map { cardDomain ->
            reduce {
                state.copy(
                    cardDetails = CardDetails.fromCardDomain(cardDomain)
                )
            }
        }
        reduce { state.copy(isLoading = false) }
    }

    fun goBack() = intent {
        postSideEffect(CardScreenEffect.GoBack)
    }

    override suspend fun interceptError(error: Exception) {
        when (error) {
            is NoInternetConnectionException -> intent {
                reduce {
                    state.copy(
                        isNoInternetConnection = true,
                        isLoading = false
                    )
                }
            }
            is ServiceUnavailableException -> intent {
                reduce {
                    state.copy(
                        isServiceUnavailable = true,
                        isLoading = false
                    )
                }
            }
            is NoSuchCardException -> intent { reduce { state.copy(isNoSuchCard = true, isLoading = false) } }
        }
    }
}