package com.velvet.tarot.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.data.cache.CacheClient
import com.velvet.data.card.CardTypes
import com.velvet.data.repo.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.receiveAsFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import kotlin.coroutines.coroutineContext

class FeedViewModel(
    private val cache: CacheClient,
    private val repository: Repository
    ) : ContainerHost<FeedState, FeedEffect>,
    ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(FeedState())

    init {
        observeCards()
        refresh()
    }

    private fun update() = intent {
        reduce {
            state.copy(cards = state.cards.filter { state.filter.filterCard(it) && it.name.contains(state.searchText, ignoreCase = true) })
        }
    }

    private fun observeCards() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            launch { repository.getCards() }
            launch {
                cache.getCardsChannel().receiveAsFlow().collect { cards ->
                    Log.d("CARDS", "observeCards collected")
                    reduce {
                        state.copy(cards = cards.filter { card -> state.filter.filterCard(card) && card.name.contains(state.searchText, ignoreCase = true) })
                    }
                }
            }
        }
    }

    fun refresh() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("CARDS", "refresh: $coroutineContext")
            repository.fetch()
        }
    }

    fun showCard(cardName: String) = intent { postSideEffect(FeedEffect.ShowCard(cardName = cardName)) }

    fun filterClick() = intent { reduce { state.copy(isExpanded = !state.isExpanded) } }

    fun setFilter(filterKey: CardTypes) = intent {
        if (filterKey == CardTypes.MAJOR) {
            reduce { state.copy(filter = state.filter.copy(isMajorEnabled = !state.filter.isMajorEnabled)) }
        } else if (filterKey == CardTypes.MINOR) {
            reduce { state.copy(filter = state.filter.copy(isMinorEnabled = !state.filter.isMinorEnabled)) }
        }
        update()
    }

    fun searchCard(searchWord: String) = intent {
        reduce { state.copy(searchText = searchWord) }
        update()
    }
}