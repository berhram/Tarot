package com.velvet.domain

import com.velvet.data.repo.Repository
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt
import com.velvet.domain.mappers.Mapper
import com.velvet.domain.states.CardDetails
import com.velvet.domain.states.CardItem

class BaseCardInteractor(
    private val repository: Repository,
    private val fromCardArtToString: Mapper<CardArt, String>,
    private val fromCardToCardDetails: Mapper<Card, CardDetails>,
    private val fromCardToCardItem: Mapper<Card, CardItem>
) : CardInteractor {

    override suspend fun cards(keyword: String): List<CardItem> = buildList {
        repository.cards(keyword).forEach {
            add(fromCardToCardItem.map(it))
        }
    }

    override suspend fun cardById(id: String): CardDetails = fromCardToCardDetails.map(repository.card(id))

    override suspend fun art(id: String): String = fromCardArtToString.map(repository.art(id))
}