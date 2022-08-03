package com.velvet.data.local.arts

import com.velvet.core.Read
import com.velvet.data.schemas.CardArt

interface CardArtStore {

    fun art(name: String): CardArt

    class Base(private val readArts: Read<List<CardArt.Base>>) : CardArtStore {

        override fun art(name: String): CardArt {
            val art = readArts.read().find { it.name == name }
            if (art != null)
                return art
            return CardArt.Empty()
        }


    }
}