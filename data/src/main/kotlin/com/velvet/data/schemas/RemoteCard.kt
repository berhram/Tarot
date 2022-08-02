package com.velvet.data.schemas

import com.google.gson.annotations.SerializedName

data class RemoteCard(
    @SerializedName("name_short")
    val nameShort: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("meaning_up")
    val meaningUp: String?,
    @SerializedName("meaning_rev")
    val meaningRev: String?,
    @SerializedName("desc")
    val description: String?
)
