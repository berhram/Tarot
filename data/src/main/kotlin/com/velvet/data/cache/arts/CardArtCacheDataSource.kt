package com.velvet.data.cache.arts

import com.velvet.core.Read
import com.velvet.data.schemas.CardArt

interface CardArtCacheDataSource {

    fun art(name: String): CardArt

    class Base(private val readArts: Read<List<CardArt>>) : CardArtCacheDataSource {

        override fun art(name: String): CardArt {
            val art = readArts.read().find { it.name == name }
            if (art != null)
                return art
            return CardArt()
        }
    }
}