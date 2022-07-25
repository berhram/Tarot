package com.velvet.domain.mappers

import com.velvet.data.schemas.Card
import com.velvet.domain.states.CardItem

class FromCardToCardItem : Mapper<Card, CardItem> {

    override fun map(data: Card): CardItem = CardItem(data.name)
}