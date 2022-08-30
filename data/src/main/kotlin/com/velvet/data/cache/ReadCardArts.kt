package com.velvet.data.cache

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.core.ManageResources
import com.velvet.core.util.Read
import com.velvet.data.schemas.CardArt
import java.lang.reflect.Type

interface ReadCardArts : Read<List<CardArt>> {

    class Base(private val gson: Gson, private val manageResources: ManageResources) :
        ReadCardArts {

        private val typeList: Type = object : TypeToken<List<CardArt>>() {}.type

        override fun read(): List<CardArt> =
            gson.fromJson(manageResources.string("ASCIIarts.json"), typeList)
    }
}