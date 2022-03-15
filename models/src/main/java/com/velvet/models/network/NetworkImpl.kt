package com.velvet.models.network

import com.velvet.models.BuildConfig
import com.velvet.models.card.CardDetails
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class NetworkImpl : Network {
    private val service: RetrofitApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BASIC
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .baseUrl("https://rws-cards-api.herokuapp.com/api/v1/")
            .build()
        service = retrofit.create(RetrofitApi::class.java)
    }

    override suspend fun getCards(number: Int) : List<CardDetails> {
        val list = service.getRandom(number).cards?.let {
            it
        } ?: kotlin.run {
            ArrayList()
        }
        return list
    }

}