package com.velvet.data.local.arts

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.data.schemas.CardArt
import java.io.IOException
import java.lang.reflect.Type

class BaseCardArtStore(appContext: Context, gson: Gson) : CardArtStore {

    private val listCardArtType: Type = object : TypeToken<List<CardArt>>() {}.type
    private val arts: List<CardArt> =
        gson.fromJson(appContext.assets.open("ASCIIarts.json").bufferedReader().use { it.readText() }, listCardArtType)

    //TODO wrapper with isEmpty fun
    override fun art(name: String): String {
        for (art in arts) {
            if (art.name == name) {
                return art.art
            }
        }
        val art = arts.find { it.name == name }

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