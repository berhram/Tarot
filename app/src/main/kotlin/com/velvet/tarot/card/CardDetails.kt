package com.velvet.tarot.card

import androidx.compose.runtime.Immutable
import com.velvet.domain.CardDomain

@Immutable
data class CardDetails(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val meaningUp: String = "",
    val meaningRev: String = "",
    val description: String = "",
    val art: String = ""
) {
    companion object {

        fun fromCardDomain(cardDomain: CardDomain): CardDetails = CardDetails(
            cardDomain.id,
            cardDomain.name,
            cardDomain.type,
            cardDomain.meaningUp,
            cardDomain.meaningRev,
            cardDomain.description,
            cardDomain.art
        )
    }
}
