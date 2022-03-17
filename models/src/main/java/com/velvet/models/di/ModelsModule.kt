package com.velvet.models.di

import com.velvet.models.network.Network
import com.velvet.models.network.NetworkImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ModelsModule {
    @Provides
    fun providesNetwork(): Network {
        return NetworkImpl()
    }
}