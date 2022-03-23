package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.data.Strings
import com.velvet.domain.usecase.FetchCardsUseCase
import com.velvet.domain.usecase.FilterCardsUseCase
import com.velvet.domain.usecase.GetAllCardsUseCase
import com.velvet.domain.usecase.SearchCardsUseCase
import com.velvet.tarot.feed.state.CardFilter
import com.velvet.tarot.feed.state.FeedState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(
    private val fetchCardsUseCase: FetchCardsUseCase,
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val searchCardsUseCase: SearchCardsUseCase,
    private val filterCardsUseCase: FilterCardsUseCase) : ContainerHost<FeedState, FeedEffect>,
    ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(
        FeedState(
        isLoading = true,
        cards = emptyList(),
        filter = CardFilter(isMajorEnabled = false, isMinorEnabled = false),
        searchText = "",
            isExpanded = false))
    private var searchJob: Job? = null

    init {
        refresh()
    }

    fun refresh() = intent {
        fetchCardsUseCase.invoke()
        val cards = getAllCardsUseCase.invoke()
        reduce {
            state.copy(isLoading = false, cards = cards, searchText = "", filter = CardFilter(isMinorEnabled = true, isMajorEnabled = true), isExpanded = false)
        }
    }

    fun showCard(cardName: String) = intent {
        postSideEffect(FeedEffect.ShowCard(cardName = cardName))
    }

    fun filterClick() = intent {
        reduce {
            state.copy(isExpanded = !state.isExpanded)
        }
    }

    fun setFilter(filter: String) = intent {
        if (filter == Strings.Major) {
            reduce { state.copy(filter = CardFilter(isMinorEnabled = state.filter.isMinorEnabled, isMajorEnabled = !state.filter.isMajorEnabled)) }
        } else {
            reduce { state.copy(filter = CardFilter(isMinorEnabled = !state.filter.isMinorEnabled, isMajorEnabled = state.filter.isMajorEnabled)) }
        }
        val cards = filterCardsUseCase(isMajor = state.filter.isMajorEnabled, isMinor = state.filter.isMinorEnabled)
        reduce {
            state.copy(cards = cards)
        }
    }

    fun searchCard(searchWord: String) = intent {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            reduce {
                state.copy(
                    searchText = searchWord
                )
            }
            val cards = searchCardsUseCase(state.searchText)
            delay(1000)
            reduce {
                state.copy(
                    cards = cards,
                )
            }
        }
    }
}