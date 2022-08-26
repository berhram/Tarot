package com.velvet.domain

import com.velvet.core.Mapper
import com.velvet.data.repo.Repository
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt
import com.velvet.domain.usecases.*

interface CardInteractor : CardDetailsUseCase, CardsUseCase, CardsByKeywordUseCase, CachedCardsUseCase {

    class Base(
        private val repository: Repository,
        private val fromCardArtToString: Mapper<CardArt, String>
    ) : CardInteractor {

        override suspend fun cachedCards(): List<CardDomain> = repository.cachedCards().map { combineToCardDomain(it) }

        override suspend fun cards(): List<CardDomain> = repository.cards().map { combineToCardDomain(it) }

        override suspend fun cards(keyword: String): List<CardDomain> {
            if (keyword.isBlank())
                return cards()
            return repository.cards(keyword).map { combineToCardDomain(it) }
        }

        private suspend fun combineToCardDomain(card: Card): CardDomain {
            var art = repository.art(card.name)
            if (art.isEmpty())
                art = repository.defaultArt()
            return CardDomain(
                card.id,
                card.name,
                card.type,
                card.meaningUp,
                card.meaningRev,
                card.description,
                fromCardArtToString.map(art)
            )
        }

        override suspend fun cardById(id: String): CardDomain = combineToCardDomain(repository.card(id))
    }
}