package com.velvet.tarot.feed

import androidx.lifecycle.viewModelScope
import com.velvet.core.viewmodel.ReactiveViewModel
import com.velvet.core.exception.NoInternetConnectionException
import com.velvet.core.exception.ServiceUnavailableException
import com.velvet.domain.usecases.CachedCardsUseCase
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
    private val cachedCardsUseCase: CachedCardsUseCase,
    private val cardsByKeywordUseCase: CardsByKeywordUseCase
) : ReactiveViewModel<FeedScreenState, FeedEffect>() {

    override val container: Container<FeedScreenState, FeedEffect> = container(
        FeedScreenState(),
        Container.Settings(intentDispatcher = Dispatchers.IO)
    )

    init {
        refresh()
    }

    private fun cachedCards() = intent {
        intercept { cachedCardsUseCase.cachedCards() }.map { cards ->
            reduce {
                state.copy(
                    cards = CardFeedList(cards.map { CardFeed.fromCardDomain(it) }),
                    isLoading = false,
                    searchText = ""
                )
            }
        }
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

    fun switchView() = intent {
        reduce { state.copy(isSimpleList = !state.isSimpleList) }
    }

    fun showCard(cardName: String) = intent { postSideEffect(FeedEffect.ShowCard(cardName = cardName)) }

    fun toggleSearch() = intent {
        if (state.isSearchExpanded)
            cachedCards()
        reduce { state.copy(searchText = "", isSearchExpanded = !state.isSearchExpanded) }
    }

    fun searchCards(searchWord: String) = intent {
        reduce { state.copy(searchText = searchWord) }
        viewModelScope.launch(Dispatchers.IO) {
            reduce { state.copy(isLoading = true) }
            intercept { cardsByKeywordUseCase.cards(searchWord.trim()) }.map { cards ->
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