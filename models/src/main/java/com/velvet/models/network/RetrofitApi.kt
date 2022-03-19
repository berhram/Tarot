package com.velvet.models.network

import com.velvet.models.card.CardListScheme
import retrofit2.http.GET

interface RetrofitApi {
    @GET("cards")
    suspend fun getCards() : CardListScheme
}