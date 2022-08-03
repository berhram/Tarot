package com.velvet.data.repo

import com.velvet.data.local.arts.CardArtStore
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt

interface Repository {

    suspend fun art(id: String): CardArt

    suspend fun card(id: String): Card

    suspend fun cards(keyword: String): List<Card>

    class Base(
        private val cardArtStore: CardArtStore,
    ) : Repository {

        override suspend fun art(id: String): CardArt = cardArtStore.art(id)

        override suspend fun card(id: String): Card {
            TODO("Not yet implemented")
        }

        override suspend fun cards(keyword: String): List<Card> {
            TODO("Not yet implemented")
        }
    }
}