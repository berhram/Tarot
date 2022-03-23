package com.velvet.data.network

import com.velvet.data.card.schemas.CardListScheme
import retrofit2.http.GET

interface RetrofitApi {
    @GET("cards")
    suspend fun getCards() : CardListScheme
}