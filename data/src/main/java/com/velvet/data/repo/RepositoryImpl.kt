package com.velvet.data.repo

import android.util.Log
import com.velvet.data.cache.CacheRepository
import com.velvet.data.card.Card
import com.velvet.data.card.CardTypes
import com.velvet.data.card.schemas.CardScheme
import com.velvet.data.network.Network
import com.velvet.data.local.arts.CardArtStore
import com.velvet.data.local.room.CardDao
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlin.coroutines.coroutineContext

class RepositoryImpl(
    private val network: Network,
    private val dao: CardDao,
    private val arts: CardArtStore,
    private val cache: CacheRepository
    ) : Repository {

    override suspend fun fetch() {
        Log.d("CARDS", "fetch invoked: $coroutineContext")
        val cardsResult = network.getCards()
        cardsResult.getOrNull()?.let {
            dao.deleteAll(cards = it.toCardList())
            dao.insertAll(cards = it.toCardList())
        }
        Log.d("CARDS", "fetch: $coroutineContext")
    }

    override suspend fun getCards() {
        Log.d("CARDS", "getCards: $coroutineContext")
        dao.getAllDistinctUntilChanged().collect {
            Log.d("CARDS", "getCards collected: $coroutineContext")
            cache.sendCards(it)
        }
    }

    override suspend fun getCard(cardName: String) {
        cache.sendCard(dao.findByName(cardName))
    }

    private fun CardScheme.toCard() : Card {
        return Card(
            type = if (this.type == "major") CardTypes.MAJOR else if (this.type == "minor") CardTypes.MINOR else throw Exception("Unknown card type"),
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
