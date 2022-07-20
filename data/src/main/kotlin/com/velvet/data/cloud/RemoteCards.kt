package com.velvet.data.cloud

import com.google.gson.annotations.SerializedName
import com.velvet.data.Card
import com.velvet.core.Read

interface RemoteCards : Read<List<Card>> {

    class Base(@SerializedName("cards") private val cards: List<Card>) : RemoteCards {

        override fun read(): List<Card> = cards
    }
}