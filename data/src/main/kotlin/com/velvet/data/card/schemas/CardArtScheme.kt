package com.velvet.data.card.schemas

import com.google.gson.annotations.SerializedName

data class CardArtScheme(
    @SerializedName("name") val name: String,
    @SerializedName("card") val art: String
)