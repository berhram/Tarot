package com.velvet.data.cloud

import com.velvet.data.schemas.RemoteCard
import retrofit2.http.GET
import retrofit2.http.Query

interface TarotService {

    @GET("search")
    fun searchByKeyword(
        @Query("q") keyword: String
    ): RemoteCard
}