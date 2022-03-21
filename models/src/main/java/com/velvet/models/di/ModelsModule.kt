package com.velvet.models.di

import android.content.Context
import androidx.room.Room
import com.velvet.models.cache.Cache
import com.velvet.models.cache.CacheImpl
import com.velvet.models.local.arts.CardArtStore
import com.velvet.models.local.arts.CardArtStoreImpl
import com.velvet.models.local.room.CardDatabase
import com.velvet.models.network.Network
import com.velvet.models.network.NetworkImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModelsModule {
    @Provides
    fun providesNetwork(): Network {
        return NetworkImpl()
    }

    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): CardDatabase {
        return Room.databaseBuilder(appContext, CardDatabase::class.java, CardDatabase.DB_NAME).build()
    }

    @Provides
    fun providesArtStore(@ApplicationContext appContext: Context): CardArtStore {
        return CardArtStoreImpl(appContext)
    }

    @Provides
    @Singleton
    fun providesCache(): Cache {
        return CacheImpl()
    }
}