package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

data class CardArt(
    @SerializedName("name") val name: String,
    @SerializedName("card") val art: String
)