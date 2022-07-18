package com.velvet.domain.states

data class CardItemState(
    val title: String,
    val onClick: (String) -> Unit
)