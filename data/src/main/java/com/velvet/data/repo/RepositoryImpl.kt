package com.velvet.data.repo

import com.velvet.data.card.Card
import com.velvet.data.card.schemas.CardScheme
import com.velvet.data.network.Network
import com.velvet.data.Strings
import com.velvet.data.local.arts.CardArtStore
import com.velvet.data.local.room.CardDao

class RepositoryImpl(
    private val network: Network,
    private val dao: CardDao,
    private val arts: CardArtStore) : Repository {

    override suspend fun fetch() {
        val cardsResult = network.getCards()
        val cards = cardsResult.getOrNull()
        if (cards != null) {
            dao.deleteAll(cards = cards.toCardList())
            dao.insertAll(cards = cards.toCardList())
        }
    }

    override suspend fun getCards() : List<Card> {
        return dao.getAll()
    }

    override suspend fun getCard(cardName: String) : Card {
        return dao.findByName(cardName)
    }

    private fun CardScheme.toCard() : Card {
        return Card(
            type = if (this.type == "major") Strings.Major else if (this.type == "minor") Strings.Minor else Strings.Unknown,
            name = this.name ?: Strings.Unknown,
            meaningUp =  this.meaningUp ?: Strings.Unknown,
            meaningRev =  this.meaningRev ?: Strings.Unknown,
            description = this.description ?: Strings.Unknown,
            art =  if (this.name != null) arts.getArt(this.name!!) else Strings.Blank
        )
    }

    private fun List<CardScheme>.toCardList() : List<Card> {
        val output: ArrayList<Card> = ArrayList()
        for (cardScheme in this) {
            output.add(cardScheme.toCard())
        }
        return output
    }


}
