package com.velvet.data.di

import androidx.room.Room
import com.google.gson.Gson
import com.velvet.core.ManageResources
import com.velvet.core.exception.HandleError
import com.velvet.data.BuildConfig
import com.velvet.data.cache.TarotCacheDataSource
import com.velvet.data.cache.CardArtCacheDataSource
import com.velvet.data.cache.ReadCardArts
import com.velvet.data.cache.ReadDefaultArt
import com.velvet.data.cache.CardDatabase
import com.velvet.data.cloud.TarotCloudDataSource
import com.velvet.data.cloud.TarotService
import com.velvet.data.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    factory<Repository> {
        Repository.Base(get(), get(), get())
    }

    factory {
        Room.databaseBuilder(androidContext(), CardDatabase::class.java, CardDatabase.DB_NAME)
            .build().cardDao()
    }

    factory {
        Gson()
    }

    factory<ManageResources> {
        ManageResources.Base(androidContext())
    }

    factory<CardArtCacheDataSource> {
        CardArtCacheDataSource.Base(get(), get())
    }

    factory<TarotCloudDataSource> {
        TarotCloudDataSource.Base(get(), get())
    }

    factory<TarotCacheDataSource> {
        TarotCacheDataSource.Base(get())
    }

    factory<HandleError> {
        HandleError.Base()
    }

    factory<ReadCardArts> {
        ReadCardArts.Base(get(), get())
    }

    factory<ReadDefaultArt> {
        ReadDefaultArt.Base()
    }

    factory<TarotService> {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
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
        retrofit.create(TarotService::class.java)
    }
}
