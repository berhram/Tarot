package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName
import com.velvet.core.util.IsEmpty

data class CardArt(
    @SerializedName("name") val name: String = "",
    @SerializedName("card") val art: String = ""
) : IsEmpty {

    override fun isEmpty(): Boolean = name == "" && art == ""
}