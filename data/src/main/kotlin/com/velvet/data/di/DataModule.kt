package com.velvet.data.di

import androidx.room.Room
import com.google.gson.Gson
import com.velvet.data.local.arts.CardArtStore
import com.velvet.data.local.arts.BaseCardArtStore
import com.velvet.data.local.room.CardDatabase
import com.velvet.data.repo.BaseRepository
import com.velvet.data.repo.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<Repository> {
        BaseRepository()
    }

    single {
        Room.databaseBuilder(androidContext(), CardDatabase::class.java, CardDatabase.DB_NAME).build().cardDao()
    }

    single {
        Gson()
    }

    single<CardArtStore> {
        BaseCardArtStore(androidContext(), get())
    }
}
