package com.velvet.domain.mappers

import com.velvet.data.schemas.Card
import com.velvet.domain.states.CardDetails

class FromCardToDetails : Mapper<Card, CardDetails> {

    override fun map(data: Card): CardDetails = CardDetails(
        name = data.name,
        nameShort = data.nameShort,
        arcana = data.type,
        meaningUp = data.meaningUp,
        meaningRev = data.meaningRev,
        description = data.description
    )
}