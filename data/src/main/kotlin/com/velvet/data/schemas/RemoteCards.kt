package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

data class RemoteCards(
    @SerializedName("cards")
    val cards: List<Card>
)
