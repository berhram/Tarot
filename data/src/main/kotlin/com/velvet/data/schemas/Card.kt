package com.velvet.data.schemas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.velvet.core.IsEmpty

@Entity
data class Card(
    @PrimaryKey
    @SerializedName("name_short")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("meaning_up")
    val meaningUp: String = "",
    @SerializedName("meaning_rev")
    val meaningRev: String = "",
    @SerializedName("desc")
    val description: String = ""
) : IsEmpty {

    override fun isEmpty(): Boolean = this.id == "" &&
            this.name == "" &&
            this.type == "" &&
            this.meaningUp == "" &&
            this.meaningRev == "" &&
            this.description == ""
}