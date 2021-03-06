package com.velvet.data.cloud

import com.google.gson.annotations.SerializedName
import com.velvet.data.schemas.Card
import com.velvet.tarot.Read

interface RemoteCards : com.velvet.tarot.Read<List<Card>> {

    class Base(@SerializedName("cards") private val cards: List<Card>) : RemoteCards {

        override fun read(): List<Card> = cards
    }
}