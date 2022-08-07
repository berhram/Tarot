package com.velvet.data.cloud

import com.velvet.data.schemas.RemoteCards
import retrofit2.http.GET
import retrofit2.http.Query

interface TarotService {

    @GET("cards/search")
    suspend fun searchByKeyword(
        @Query("q") keyword: String
    ): RemoteCards

    @GET("cards")
    suspend fun allCards(): RemoteCards
}