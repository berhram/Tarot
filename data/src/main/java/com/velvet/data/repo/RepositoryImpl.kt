package com.velvet.data.repo

import com.velvet.data.card.Card
import com.velvet.data.card.CardTypes
import com.velvet.data.card.schemas.CardScheme
import com.velvet.data.network.Network
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
            type = if (this.type == "major") CardTypes.MAJOR else if (this.type == "minor") CardTypes.MINOR else CardTypes.NONE,
            name = this.name ?: "",
            meaningUp =  this.meaningUp ?: "",
            meaningRev =  this.meaningRev ?: "",
            description = this.description ?: "",
            art =  arts.getArt(this.name) ?: "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n" +
            "XXXXXXXXXXXXXXXXXXXXX\n"
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
