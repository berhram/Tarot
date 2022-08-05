package com.velvet.data.cloud

import com.velvet.data.schemas.RemoteCards
import retrofit2.http.GET
import retrofit2.http.Query

interface TarotService {

    @GET("search")
    suspend fun searchByKeyword(
        @Query("q") keyword: String
    ): RemoteCards

    @GET
    suspend fun allCards(): RemoteCards
}