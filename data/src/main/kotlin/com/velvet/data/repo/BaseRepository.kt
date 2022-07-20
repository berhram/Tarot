package com.velvet.data.repo

import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt

class BaseRepository(

) : Repository {
    override suspend fun art(id: String): CardArt {
        TODO("Not yet implemented")
    }

    override suspend fun card(id: String): Card {
        TODO("Not yet implemented")
    }

    override suspend fun cards(keyword: String): List<Card> {
        TODO("Not yet implemented")
    }
}
