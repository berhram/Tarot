package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.data.cache.CacheClient
import com.velvet.data.card.CardTypes
import com.velvet.data.repo.Repository
import com.velvet.data.repo.Status
import com.velvet.domain.CardsUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.receiveAsFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(
    private val cardsUseCase: CardsUseCase
) : ContainerHost<FeedState, FeedEffect>,
    ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(FeedState())

    init {
        refresh()
    }


    fun refresh() = intent { viewModelScope.launch(Dispatchers.IO) { cardsUseCase.cards(state.filter) } }

    fun showCard(cardName: String) = intent { postSideEffect(FeedEffect.ShowCard(cardName = cardName)) }

    fun filterClick() = intent { reduce { state.copy(isExpanded = !state.isExpanded) } }

//    fun setFilter(filterKey: CardTypes) = intent {
//        if (filterKey == CardTypes.MAJOR) {
//            reduce { state.copy(filter = state.filter.copy(isMajorEnabled = !state.filter.isMajorEnabled)) }
//        } else if (filterKey == CardTypes.MINOR) {
//            reduce { state.copy(filter = state.filter.copy(isMinorEnabled = !state.filter.isMinorEnabled)) }
//        }
//    }

    fun searchCard(searchWord: String) = intent { reduce { state.copy(searchText = searchWord) } }
}