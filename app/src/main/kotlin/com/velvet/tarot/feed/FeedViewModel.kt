package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.domain.usecases.CardsTitlesByNameUseCase
import kotlinx.coroutines.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(
    private val cardsByNameUseCase: CardsTitlesByNameUseCase
) : ContainerHost<FeedState, FeedEffect>,
    ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(FeedState())

    init {
        refresh()
    }

    fun refresh() = intent { viewModelScope.launch(Dispatchers.IO) { cardsByNameUseCase.cardsByName("") } }

    fun showCard(cardName: String) = intent { postSideEffect(FeedEffect.ShowCard(cardName = cardName)) }

    fun searchCard(searchWord: String) =
        intent { reduce { state.copy(cards = state.cards.copy(cards = cardsByNameUseCase.cardsByName(searchWord))) } }
}