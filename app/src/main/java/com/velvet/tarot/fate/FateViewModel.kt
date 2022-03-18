package com.velvet.tarot.fate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velvet.models.card.Card
import com.velvet.models.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FateViewModel @Inject constructor(private val repository: Repository) : ContainerHost<FateScreenState, FateScreenEffect>, ViewModel() {

    override val container = container<FateScreenState, FateScreenEffect>(FateScreenState(card = Card.initial(), isAnimated = true))

    fun getCard() = intent {
        val card = repository.getNewCard()
        reduce {
            state.copy(card = card, isAnimated = false)
        }
    }
}