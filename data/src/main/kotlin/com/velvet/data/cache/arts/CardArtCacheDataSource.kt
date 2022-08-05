package com.velvet.data.cache.arts

import com.velvet.core.Read
import com.velvet.data.exception.NoSuchArtException
import com.velvet.data.schemas.CardArt

interface CardArtCacheDataSource {

    suspend fun art(name: String): CardArt

    suspend fun defaultArt(): CardArt

    class Base(private val arts: Read<List<CardArt>>, private val defaultArt: Read<CardArt>) : CardArtCacheDataSource {

        override suspend fun art(name: String): CardArt {
            val art = arts.read().find { it.name == name }
            if (art != null)
                return art
            throw NoSuchArtException()
        }

        override suspend fun defaultArt(): CardArt = defaultArt.read()
    }
}