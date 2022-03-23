package com.velvet.data.local.arts

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.data.Strings
import com.velvet.data.card.schemas.CardArtScheme
import java.io.IOException
import java.lang.reflect.Type

class CardArtStoreImpl(appContext: Context) : CardArtStore {
    private val gson = Gson()
    private val listCardArtType: Type = object : TypeToken<List<CardArtScheme>>() {}.type
    private val arts: List<CardArtScheme> = gson.fromJson(appContext.assets.open("ASCIIarts.json").bufferedReader().use { it.readText() }, listCardArtType)

    override fun getArt(name: String) : String {
        try {
            for (art in arts) {
                if (art.name == name) {
                    return art.art
                }
            }
            return Strings.Blank
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return Strings.Blank
        }
    }



}