package com.velvet.models.repo

import com.velvet.models.card.Card
import com.velvet.models.card.CardDetailsScheme
import com.velvet.models.local.room.CardDatabase
import com.velvet.models.network.Network
import com.velvet.models.Strings
import com.velvet.models.local.arts.CardArtStore
import javax.inject.Inject
import kotlin.random.Random

class RepositoryImpl @Inject constructor(private val network: Network, private val database: CardDatabase, private val arts: CardArtStore) : Repository {
    private val dao = database.cardDao()

    override suspend fun getCards(): List<Card> {
        return network.getCards().toCardList()
    }

    override suspend fun getCard(cardName: String): Card {
        return dao.findByName(cardName)
    }

    private fun CardDetailsScheme.toCard() : Card {
        return Card(
            type = if (this.type == "major") "Major" else if (this.type == "minor") "Minor" else Strings.Unknown,
            name = this.name ?: Strings.Unknown,
            meaningUp =  this.meaningUp ?: Strings.Unknown,
            meaningRev =  this.meaningUp ?: Strings.Unknown,
            description = this.description ?: Strings.Unknown,
            art =  if (this.name != null) arts.getArt(this.name!!) else Strings.Blank
        )
    }

    private fun List<CardDetailsScheme>.toCardList() : List<Card> {
        val output: ArrayList<Card> = ArrayList()
        for (cardScheme in this) {
            output.add(cardScheme.toCard())
        }
        return output
    }


}
