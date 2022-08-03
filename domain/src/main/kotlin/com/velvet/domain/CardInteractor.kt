package com.velvet.domain

import com.velvet.core.Mapper
import com.velvet.data.repo.Repository
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt
import com.velvet.domain.usecases.AllCardsUseCase
import com.velvet.domain.usecases.CardArtUseCase
import com.velvet.domain.usecases.CardsByKeywordUseCase
import com.velvet.domain.usecases.CardDetailsUseCase

interface CardInteractor : CardDetailsUseCase, CardsByKeywordUseCase, CardArtUseCase, AllCardsUseCase {

    class Base(
        private val repository: Repository,
        private val fromCardArtToString: Mapper<CardArt, String>
    ) : CardInteractor {

        override suspend fun cards(): List<Card> = repository.cards()

        override suspend fun cards(keyword: String): List<Card> = repository.cards(keyword)

        override suspend fun cardById(id: String): Card = repository.card(id)

        override suspend fun art(id: String): String = fromCardArtToString.map(repository.art(id))
    }
}