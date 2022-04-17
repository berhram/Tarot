package com.velvet.data.di

import androidx.room.Room
import com.velvet.data.cache.CacheClient
import com.velvet.data.cache.CacheImpl
import com.velvet.data.cache.CacheRepository
import com.velvet.data.local.arts.CardArtStore
import com.velvet.data.local.arts.CardArtStoreImpl
import com.velvet.data.local.room.CardDatabase
import com.velvet.data.network.Network
import com.velvet.data.network.NetworkImpl
import com.velvet.data.repo.Repository
import com.velvet.data.repo.RepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.binds
import org.koin.dsl.module

val dataModule = module {
    factory<Repository> {
        RepositoryImpl(
            network = get(),
            dao = get(),
            arts = get(),
            cache = get()
        )
    }

    factory<Network> {
        NetworkImpl()
    }

    factory {
        Room.databaseBuilder(androidContext(), CardDatabase::class.java, CardDatabase.DB_NAME).build().cardDao()
    }

    factory<CardArtStore> {
        CardArtStoreImpl(androidContext())
    }

    single { CacheImpl() }.binds(arrayOf(CacheClient::class, CacheRepository::class))
}
