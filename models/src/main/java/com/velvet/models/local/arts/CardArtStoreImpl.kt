package com.velvet.models.local.arts

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.models.Strings
import com.velvet.models.card.CardArtScheme
import java.io.IOException
import javax.inject.Inject

class CardArtStoreImpl(appContext: Context) : CardArtStore {
    val gson = Gson()
    val listCardArtType = object : TypeToken<List<CardArtScheme>>() {}.type
    val arts: List<CardArtScheme> = gson.fromJson(appContext.assets.open("ASCIIarts.json").bufferedReader().use { it.readText() }, listCardArtType)

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