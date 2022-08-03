package com.velvet.core

import android.content.Context
import androidx.annotation.StringRes

interface ManageResources {

    fun string(@StringRes id: Int): String

    fun string(fileName: String): String

    class Base(private val context: Context) : ManageResources {
        override fun string(id: Int) = context.getString(id)

        override fun string(fileName: String): String =
            context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}