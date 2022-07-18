package com.velvet.data.card.schemas

import com.google.gson.annotations.SerializedName

data class CardScheme(
    @SerializedName("type") val type: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("meaning_up") val meaningUp: String?,
    @SerializedName("meaning_rev") val meaningRev: String?,
    @SerializedName("desc") val description: String?,
)