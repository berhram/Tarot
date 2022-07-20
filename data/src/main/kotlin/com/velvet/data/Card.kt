package com.velvet.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Card(
    @SerializedName("name_short")
    @PrimaryKey
    val nameShort: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("value_int")
    val valueInt: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("meaning_up")
    val meaningUp: String,
    @SerializedName("meaning_rev")
    val meaningRev: String,
    @SerializedName("desc")
    val description: String
)