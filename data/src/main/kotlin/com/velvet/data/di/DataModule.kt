package com.velvet.data.di

import androidx.room.Room
import com.google.gson.Gson
import com.velvet.core.ManageResources
import com.velvet.core.Read
import com.velvet.core.exception.HandleError
import com.velvet.data.cache.TarotCacheDataSource
import com.velvet.data.cache.arts.CardArtCacheDataSource
import com.velvet.data.cache.arts.ReadCardArts
import com.velvet.data.cache.arts.ReadDefaultArt
import com.velvet.data.cache.room.CardDatabase
import com.velvet.data.cloud.TarotCloudDataSource
import com.velvet.data.cloud.TarotService
import com.velvet.data.repo.Repository
import com.velvet.data.schemas.CardArt
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dataModule = module {

    factory<Repository> {
        Repository.Base(get(), get(), get())
    }

    factory {
        Room.databaseBuilder(androidContext(), CardDatabase::class.java, CardDatabase.DB_NAME).build().cardDao()
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

    factory<Read<List<CardArt>>> {
        ReadCardArts(get(), get())
    }

    factory<Read<CardArt>> {
        ReadDefaultArt()
    }

    factory<GsonConverterFactory> {
        GsonConverterFactory.create()
    }

    factory {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
    }

    factory<TarotService> {
        Retrofit.Builder().baseUrl("https://rws-cards-api.herokuapp.com/api/v1/cards").addConverterFactory(get())
            .client(get()).build().create()
    }
}
