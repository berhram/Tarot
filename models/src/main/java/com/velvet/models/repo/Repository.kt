package com.velvet.models.repo

import com.velvet.models.card.Card

interface Repository {
    suspend fun getOldCard() : Card
    suspend fun getNewCard() : Card
}