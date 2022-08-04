package com.velvet.domain.mappers

import com.velvet.core.Mapper
import com.velvet.data.schemas.CardArt

class FromCardArtToString : Mapper<CardArt, String> {

    override fun map(data: CardArt): String = data.art
}