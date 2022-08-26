package com.velvet.tarot.nav

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Routing : Parcelable {

    @Parcelize
    object FeedScreen : Routing()

    @Parcelize
    class CardScreen(val cardId: String) : Routing()
}
