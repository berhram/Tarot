package com.velvet.data.cache.arts

import com.velvet.core.Read
import com.velvet.data.exception.NoSuchArtException
import com.velvet.data.schemas.CardArt

interface CardArtCacheDataSource {

    fun art(name: String): CardArt

    fun defaultArt(): CardArt

    class Base(private val readArts: Read<List<CardArt>>) : CardArtCacheDataSource {

        override fun art(name: String): CardArt {
            val art = readArts.read().find { it.name == name }
            if (art != null)
                return art
            throw NoSuchArtException()
        }

        override fun defaultArt(): CardArt = CardArt("default", BLANK_ART)

        companion object {
            const val BLANK_ART = "XXXXXXXXXXXXXXXXXXXXX\n" +
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
        }
    }
}