package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.domain.usecases.CardItemsUseCase
import kotlinx.coroutines.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(
    private val cardsByNameUseCase: CardItemsUseCase
) : ContainerHost<FeedScreenState, FeedEffect>, ViewModel() {

    override val container: Container<FeedScreenState, FeedEffect> = container(FeedScreenState())

    private var searchJob: Job? = null

    init {
        searchCards("")
    }

    fun showCard(cardName: String) = intent { postSideEffect(FeedEffect.ShowCard(cardName = cardName)) }

    fun searchCards(searchWord: String) = intent {

        searchJob?.cancel()

        reduce { state.copy(isLoading = true, searchText = "") }

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val cards = cardsByNameUseCase.cards(searchWord)
            reduce { state.copy(isLoading = false, cards = cards) }
        }
    }
}