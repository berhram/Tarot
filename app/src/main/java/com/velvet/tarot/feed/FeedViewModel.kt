package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import com.velvet.domain.usecase.FetchCardsUseCase
import com.velvet.domain.usecase.GetAllCardsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(private val fetchCardsUseCase: FetchCardsUseCase, private val getAllCardsUseCase: GetAllCardsUseCase) : ContainerHost<FeedState, FeedEffect>, ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(FeedState(
        isLoading = true,
        cards = emptyList()))

    init {
        refresh()
    }

    fun refresh() = intent {
        fetchCardsUseCase.invoke()
        val cards = getAllCardsUseCase.invoke()
        reduce {
            state.copy(isLoading = false, cards = cards)
        }
    }

    fun showCard(cardName: String) = intent {
        postSideEffect(FeedEffect.ShowCard(cardName = cardName))
    }
}