package com.velvet.data.repo

import com.velvet.data.cache.TarotCacheDataSource
import com.velvet.data.cloud.TarotCloudDataSource
import com.velvet.data.cache.arts.CardArtCacheDataSource
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt

interface Repository {

    suspend fun cachedCards(): List<Card>

    suspend fun art(name: String): CardArt

    suspend fun card(id: String): Card

    suspend fun cards(): List<Card>

    suspend fun cards(keyword: String): List<Card>

    suspend fun defaultArt(): CardArt

    class Base(
        private val cardArtCacheDataSource: CardArtCacheDataSource,
        private val tarotCloudDataSource: TarotCloudDataSource,
        private val tarotCacheDataSource: TarotCacheDataSource
    ) : Repository {

        override suspend fun cachedCards(): List<Card> = tarotCacheDataSource.cards()

        override suspend fun defaultArt(): CardArt = cardArtCacheDataSource.defaultArt()

        override suspend fun art(name: String): CardArt = cardArtCacheDataSource.art(name)

        override suspend fun card(id: String): Card = tarotCacheDataSource.card(id)

        override suspend fun cards(keyword: String): List<Card> = tarotCloudDataSource.cards(keyword)

        override suspend fun cards(): List<Card> {
            val cloudCards = tarotCloudDataSource.cards()
            tarotCacheDataSource.save(cloudCards)
            return tarotCacheDataSource.cards()
        }
    }
}