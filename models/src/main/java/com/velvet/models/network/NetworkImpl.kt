package com.velvet.models.network

import android.util.Log
import com.velvet.models.BuildConfig
import com.velvet.models.card.CardDetailsScheme
import com.velvet.models.card.CardListScheme
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class NetworkImpl @Inject constructor(interceptor: ConnectionInterceptor) : Network {
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
        //httpClient.addInterceptor(interceptor)
        Log.d("HTTP", "add interceptor")
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        Log.d("HTTP", "builder")
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .baseUrl("https://rws-cards-api.herokuapp.com/api/v1/")
            .build()
        service = retrofit.create(RetrofitApi::class.java)
    }

    private fun CardListScheme.toListSchemas() : List<CardDetailsScheme> {
        val cards = this.cards?.let {
            it
        } ?: kotlin.run {
            ArrayList()
        }
        return cards
    }

    override suspend fun getCards() : Result<List<CardDetailsScheme>> {
        Log.d("HTTP", "getCards invoked")
        val cards = service.getCards().awaitResponse().body()
        return if (cards != null) {
            Result.success(cards.toListSchemas())
        } else {
            Result.failure(Exception("Can't retrieve cards"))
        }
    }
}

