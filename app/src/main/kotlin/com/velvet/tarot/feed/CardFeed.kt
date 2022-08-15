package com.velvet.tarot.feed

import com.velvet.domain.CardDomain

data class CardFeed(
    val id: String,
    val art: String,
    val name: String
) {
    companion object {

        fun fromCardDomain(cardDomain: CardDomain): CardFeed = CardFeed(cardDomain.id, cardDomain.art, cardDomain.name)
    }
}
