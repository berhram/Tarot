package com.velvet.data.local.arts

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.data.CardArtScheme
import java.io.IOException
import java.lang.reflect.Type

class BaseCardArtStore(appContext: Context, gson: Gson) : CardArtStore {

    private val listCardArtType: Type = object : TypeToken<List<CardArtScheme>>() {}.type
    private val arts: List<CardArtScheme> =
        gson.fromJson(appContext.assets.open("ASCIIarts.json").bufferedReader().use { it.readText() }, listCardArtType)

    override fun art(name: String): String {
        try {
            for (art in arts) {
                if (art.name == name) {
                    return art.art
                }
            }
            return BLANK_ART
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return BLANK_ART
        }
    }

    companion object {
        const val BLANK_ART = "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n" +
                "XXXXXXXXXXXXXXXXXXXXX\n"
    }
}