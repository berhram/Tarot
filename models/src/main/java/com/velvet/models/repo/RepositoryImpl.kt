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

    override suspend fun getNewCard(): Card {
        val card = network.getCard().toCard()
        dao.insert(card)
        return card
    }

    override suspend fun getOldCard(): Card {
        return dao.getLast()
    }

    private fun CardDetailsScheme.toCard() : Card {
        val isReversed = Random.nextBoolean()
        val name = (if (isReversed) this.name + " " + Strings.Reversed else this.name) ?: Strings.Unknown
        return Card(
            type = if (this.type == "major") "Major" else if (this.type == "minor") "Minor" else Strings.Unknown,
            name = name,
            meaning = (if (isReversed) this.meaningRev else this.meaningUp) ?: Strings.Unknown,
            description = this.description ?: Strings.Unknown,
            time = System.currentTimeMillis(),
            art = arts.getArt(name)
        )
    }
}
