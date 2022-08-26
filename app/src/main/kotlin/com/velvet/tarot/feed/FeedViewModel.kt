package com.velvet.tarot.feed

import androidx.lifecycle.viewModelScope
import com.velvet.core.ReactiveViewModel
import com.velvet.core.exception.NoInternetConnectionException
import com.velvet.core.exception.ServiceUnavailableException
import com.velvet.domain.usecases.CardsByKeywordUseCase
import com.velvet.domain.usecases.CardsUseCase
import kotlinx.coroutines.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(
    private val cardsUseCase: CardsUseCase,
    private val cardsByKeywordUseCase: CardsByKeywordUseCase
) : ReactiveViewModel<FeedScreenState, FeedEffect>() {

    private var searchJob: Job? = null

    override val container: Container<FeedScreenState, FeedEffect> = container(
        FeedScreenState(),
        Container.Settings(intentDispatcher = Dispatchers.IO)
    )

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(isLoading = true, isServiceUnavailable = false, isNoInternetConnection = false) }
        intercept { cardsUseCase.cards() }.map { cards ->
            reduce {
                state.copy(
                    cards = CardFeedList(cards.map { CardFeed.fromCardDomain(it) }),
                    isLoading = false,
                    searchText = ""
                )
            }
        }
    }


    fun showCard(cardName: String) = intent { postSideEffect(FeedEffect.ShowCard(cardName = cardName)) }

    fun toggleSearch() = intent { reduce { state.copy() } }

    fun searchCards(searchWord: String) = intent {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            reduce { state.copy(isLoading = true, searchText = searchWord) }
            intercept { cardsByKeywordUseCase.cards(searchWord) }.map { cards ->
                reduce {
                    state.copy(
                        isLoading = false,
                        cards = CardFeedList(cards.map { CardFeed.fromCardDomain(it) })
                    )
                }
            }
        }
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
        }
    }
}