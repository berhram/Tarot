package com.velvet.data.cache.arts

import com.velvet.data.schemas.CardArt

interface CardArtCacheDataSource {

    suspend fun art(name: String): CardArt

    suspend fun defaultArt(): CardArt

    class Base(private val arts: ReadCardArts, private val defaultArt: ReadDefaultArt) : CardArtCacheDataSource {

        override suspend fun art(name: String): CardArt {
            val art = arts.read().find { it.name == name }
            if (art != null)
                return art
            return CardArt()
        }

        override suspend fun defaultArt(): CardArt = defaultArt.read()
    }
}