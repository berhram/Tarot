package com.velvet.domain.states

import com.velvet.core.Mapper
import com.velvet.data.Card

data class CardItemState(
    val title: String,
    val onClick: (String) -> Unit
) : Mapper<Card, CardItemState> {

    override fun map(data: Card): CardItemState = CardItemState(
        title = data.name,
        onClick =
    )
}