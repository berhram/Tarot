package com.velvet.models.network

import com.velvet.models.card.CardList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("random")
    suspend fun getRandom(@Query("n") number: Int) : CardList
}