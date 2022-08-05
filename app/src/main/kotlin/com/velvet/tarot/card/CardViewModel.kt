package com.velvet.tarot.card

import com.velvet.core.ReactiveViewModel
import com.velvet.core.exception.NoInternetConnectionException
import com.velvet.core.exception.ServiceUnavailableException
import com.velvet.data.exception.NoSuchArtException
import com.velvet.data.exception.NoSuchCardException
import com.velvet.domain.usecases.CardArtUseCase
import com.velvet.domain.usecases.CardDetailsUseCase
import com.velvet.domain.usecases.DefaultArtUseCase
import kotlinx.coroutines.Dispatchers
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CardViewModel(
    private val cardId: String,
    private val cardDetailsUseCase: CardDetailsUseCase,
    private val cardArtUseCase: CardArtUseCase,
    private val defaultArtUseCase: DefaultArtUseCase
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
        intercept { cardDetailsUseCase.cardById(cardId) }.map { details -> reduce { state.copy(cardDetails = details) } }
        intercept { cardArtUseCase.art(cardId) }.map { art -> reduce { state.copy(art = art) } }
        reduce { state.copy(isLoading = false) }
    }

    fun goBack() = intent {
        postSideEffect(CardScreenEffect.GoBack)
    }

    private fun defaultArt() = intent {
        intercept { defaultArtUseCase.defaultArt() }.map { art -> reduce { state.copy(art = art) } }
    }

    override suspend fun interceptError(error: Exception) {
        when (error) {
            is NoInternetConnectionException -> intent { reduce { state.copy(isNoInternetConnection = true) } }
            is ServiceUnavailableException -> intent { reduce { state.copy(isServiceUnavailable = true) } }
            is NoSuchArtException -> defaultArt()
            is NoSuchCardException -> intent { reduce { state.copy(isNoSuchCard = true) } }
        }
    }
}