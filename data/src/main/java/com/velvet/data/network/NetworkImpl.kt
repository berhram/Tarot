package com.velvet.data.network

import com.velvet.data.BuildConfig
import com.velvet.data.card.schemas.CardScheme
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkImpl : Network {
    private var service: RetrofitApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BASIC
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .baseUrl("https://rws-cards-api.herokuapp.com/api/v1/")
            .build()
        service = retrofit.create(RetrofitApi::class.java)
    }

    override suspend fun getCards() : Result<List<CardScheme>> {
        return try {
            val cards = service.getCards().cards ?: run {
                ArrayList()
            }
            Result.success(cards)
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}