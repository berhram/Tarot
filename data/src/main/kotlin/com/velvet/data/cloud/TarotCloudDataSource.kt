package com.velvet.data.cloud

import com.velvet.core.CloudDataSource
import com.velvet.core.exception.HandleError
import com.velvet.data.schemas.Card

interface TarotCloudDataSource {

    suspend fun cards(keyword: String): List<Card>

    suspend fun cards(): List<Card>

    class Base(
        private val tarotService: TarotService,
        handleError: HandleError
    ) : TarotCloudDataSource,
        CloudDataSource.Abstract(handleError) {

        override suspend fun cards(keyword: String): List<Card> = handle { tarotService.searchByKeyword(keyword).cards }

        override suspend fun cards(): List<Card> = handle { tarotService.allCards().cards }
    }
}