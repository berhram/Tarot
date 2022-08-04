package com.velvet.data.repo

import com.velvet.data.cache.TarotCacheDataSource
import com.velvet.data.cloud.TarotCloudDataSource
import com.velvet.data.cache.arts.CardArtCacheDataSource
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt

interface Repository {

    suspend fun art(id: String): CardArt

    suspend fun card(id: String): Card

    suspend fun cards(): List<Card>

    suspend fun cards(keyword: String): List<Card>

    class Base(
        private val cardArtCacheDataSource: CardArtCacheDataSource,
        private val tarotCloudDataSource: TarotCloudDataSource,
        private val tarotCacheDataSource: TarotCacheDataSource
    ) : Repository {

        override suspend fun art(id: String): CardArt = cardArtCacheDataSource.art(id)

        override suspend fun card(id: String): Card = tarotCacheDataSource.card(id)

        override suspend fun cards(keyword: String): List<Card> = tarotCloudDataSource.cards(keyword)

        override suspend fun cards(): List<Card> {
            val cloudCards = tarotCloudDataSource.cards()
            tarotCacheDataSource.save(cloudCards)
            return tarotCacheDataSource.cards()
        }
    }
}