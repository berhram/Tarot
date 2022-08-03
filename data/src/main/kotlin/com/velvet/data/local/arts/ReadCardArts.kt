package com.velvet.data.local.arts

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velvet.core.ManageResources
import com.velvet.core.Read
import com.velvet.data.schemas.CardArt
import java.lang.reflect.Type

class ReadCardArts(private val gson: Gson, private val manageResources: ManageResources) : Read<List<CardArt.Base>> {

    private val typeList: Type = object : TypeToken<List<CardArt.Base>>() {}.type

    override fun read(): List<CardArt.Base> = gson.fromJson(manageResources.string(""), typeList)
}