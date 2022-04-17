package com.velvet.tarot.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.data.cache.CacheClient
import com.velvet.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CardViewModel(
    private val cardName: String,
    private val cache: CacheClient,
    private val repository: Repository
    ) : ContainerHost<CardState, CardEffect>, ViewModel() {
    override val container = container<CardState, CardEffect>(CardState())

    init {
        intent {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getCard(cardName)
                cache.getCardChannel().receiveCatching().onSuccess { reduce { state.copy(card = it, isLoading = false) } }.onFailure { goBack() }
            }
        }
    }

    fun goBack() = intent {
        postSideEffect(CardEffect.GoBack)
    }
}