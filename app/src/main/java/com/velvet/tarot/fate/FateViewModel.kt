package com.velvet.tarot.fate

import androidx.lifecycle.viewModelScope
import com.velvet.models.network.Network
import com.velvet.mvi.MviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FateViewModel(private val repo: Network) : MviViewModel<FateContract.Event,
        FateContract.State,
        FateContract.Effect>() {
    override fun createInitialState(): FateContract.State {
        return FateContract.State(
            FateContract.FateState.Idle
        )
    }

    override fun handleEvent(event: FateContract.Event) {
        when (event) {
            is FateContract.Event.OnOneCardButtonClicked -> { getCards(GuessingTypes.ONE) }
            is FateContract.Event.OnThreeCardButtonClicked -> { getCards(GuessingTypes.THREE) }
        }
    }

    private fun getCards(type: GuessingTypes) {
        viewModelScope.launch {
            setState { copy(fateState = FateContract.FateState.Loading) }
            try {
                val number = if (type == GuessingTypes.ONE) 1 else if (type == GuessingTypes.THREE) 3 else 0
                val result = withContext(Dispatchers.IO) {
                    repo.getCards(number)
                }
                setState {
                    copy( fateState = FateContract.FateState.Success(type = type, cards = result))
                }
            } catch (exception: Exception) {
                setEffect { FateContract.Effect.ShowToast }
            }
        }
    }
}