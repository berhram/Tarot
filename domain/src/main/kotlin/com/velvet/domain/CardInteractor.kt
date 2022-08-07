package com.velvet.domain

import com.velvet.core.Mapper
import com.velvet.data.repo.Repository
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt
import com.velvet.domain.usecases.*

interface CardInteractor : CardDetailsUseCase, CardsByKeywordUseCase, CardArtUseCase, AllCardsUseCase, DefaultArtUseCase {

    class Base(
        private val repository: Repository,
        private val fromCardArtToString: Mapper<CardArt, String>
    ) : CardInteractor {

        override suspend fun cards(): List<Card> = repository.cards()

        override suspend fun cards(keyword: String): List<Card> = repository.cards(keyword)

        override suspend fun cardById(id: String): Card = repository.card(id)

        override suspend fun art(name: String): String = fromCardArtToString.map(repository.art(name))

        override suspend fun defaultArt(): String = fromCardArtToString.map(repository.defaultArt())
    }
}