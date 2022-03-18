package com.velvet.models.card

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//TODO try with data class
class CardDetailsScheme {
    @Expose
    @SerializedName("type")
    var type: String? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("meaning_up")
    var meaningUp: String? = null

    @Expose
    @SerializedName("meaning_rev")
    var meaningRev: String? = null

    @Expose
    @SerializedName("desc")
    var description: String? = null
}