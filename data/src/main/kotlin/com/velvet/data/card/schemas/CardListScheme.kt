package com.velvet.data.card.schemas

import com.google.gson.annotations.SerializedName

data class CardListScheme(
    @SerializedName("nhits") val nhits: Int?,
    @SerializedName("cards") val cards: List<CardScheme>
)