package com.velvet.tarot.fate.di

import com.velvet.models.local.arts.CardArtStore
import com.velvet.models.local.room.CardDatabase
import com.velvet.models.network.Network
import com.velvet.models.repo.Repository
import com.velvet.models.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FateModule {
    @Provides
    fun providesRepo(network: Network, database: CardDatabase, arts: CardArtStore): Repository {
        return RepositoryImpl(network = network, database = database, arts = arts)
    }
}