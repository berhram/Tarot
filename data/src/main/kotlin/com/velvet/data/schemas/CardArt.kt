package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName
import com.velvet.core.IsEmpty
import com.velvet.core.Read

interface CardArt : IsEmpty, Read<String> {

    data class Base(
        @SerializedName("name") val name: String,
        @SerializedName("card") val art: String
    ) : CardArt {

        override fun read(): String = art

        override fun isEmpty(): Boolean = false
    }

    class Empty : CardArt {

        override fun read(): String = ""

        override fun isEmpty(): Boolean = true
    }
}