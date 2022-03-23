package com.velvet.data.card.schemas

import com.google.gson.annotations.SerializedName

data class CardScheme (
    @SerializedName("type") val type: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("name_short") val nameShort: String? = null,
    @SerializedName("value") val value: String? = null,
    @SerializedName("value_int") val valueInt: Int? = null,
    @SerializedName("meaning_up") val meaningUp: String? = null,
    @SerializedName("meaning_rev") val meaningRev: String? = null,
    @SerializedName("desc") val description: String? = null,
)