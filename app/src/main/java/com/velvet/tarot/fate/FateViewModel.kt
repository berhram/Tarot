package com.velvet.tarot.fate

import androidx.lifecycle.ViewModel
import com.velvet.models.card.CardDetails
import com.velvet.models.card.CardDetailsScheme
import com.velvet.models.network.Network
import com.velvet.tarot.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FateViewModel @Inject constructor(private val repo: Network) : ContainerHost<FateScreenState, FateScreenEffect>, ViewModel() {

    override val container = container<FateScreenState, FateScreenEffect>(FateScreenState())

    fun getCards(layout: GuessingTypes) = intent {
        val data = repo.getCards(layout.cards).toCardDetails()
        reduce {
            val oldMap = state.cards.toMutableMap()
            oldMap[layout] = data
            state.copy(layoutType = layout, cards = oldMap)
        }
    }

    fun changeLayout(layoutType: GuessingTypes) = intent {
        reduce {
            state.copy(layoutType = layoutType)
        }
    }

    private fun List<CardDetailsScheme>.toCardDetails() : List<CardDetails> {
        val outputList = ArrayList<CardDetails>()
        for (scheme in this) {
            outputList.add(CardDetails(
                type = scheme.type ?: Constants.Unknown,
                name = scheme.name ?: Constants.Unknown,
                nameShort = scheme.nameShort ?: Constants.Unknown,
                meaningUp = scheme.meaningUp ?: Constants.Unknown,
                meaningRev = scheme.meaningRev ?: Constants.Unknown,
                description = scheme.description ?: Constants.Unknown
            ))
        }
    }
}