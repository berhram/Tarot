package com.velvet.data.repo

import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt

interface Repository {

    suspend fun art(id: String): CardArt

    suspend fun card(id: String): Card

    suspend fun cards(keyword: String): List<Card>
}