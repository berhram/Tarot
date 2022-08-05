package com.velvet.data.cache.arts

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.core.ManageResources
import com.velvet.core.Read
import com.velvet.data.schemas.CardArt
import java.lang.reflect.Type

//TODO filename
class ReadCardArts(private val gson: Gson, private val manageResources: ManageResources) : Read<List<CardArt>> {

    private val typeList: Type = object : TypeToken<List<CardArt>>() {}.type

    override fun read(): List<CardArt> = gson.fromJson(manageResources.string(""), typeList)
}