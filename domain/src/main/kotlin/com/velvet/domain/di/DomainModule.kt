package com.velvet.domain.di

import com.velvet.core.Mapper
import com.velvet.data.schemas.CardArt
import com.velvet.domain.CardInteractor
import com.velvet.domain.mappers.FromCardArtToString
import com.velvet.domain.usecases.*
import org.koin.dsl.binds
import org.koin.dsl.module

val domainModule = module {

    factory<Mapper<CardArt, String>> {
        FromCardArtToString()
    }

    single {
        CardInteractor.Base(get(), get())
    }.binds(
        arrayOf(
            CardArtUseCase::class,
            CardDetailsUseCase::class,
            CardsByKeywordUseCase::class,
            AllCardsUseCase::class,
            DefaultArtUseCase::class
        )
    )
}