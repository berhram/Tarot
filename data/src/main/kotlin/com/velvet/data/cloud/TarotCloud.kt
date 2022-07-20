package com.velvet.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface TarotCloud {

    @GET("search")
    fun searchByKeyword(
        @Query("q") keyword: String
    ): RemoteCards.Base
}