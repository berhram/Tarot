package com.velvet.domain.di

import com.velvet.domain.mappers.Mapper
import com.velvet.data.schemas.Card
import com.velvet.data.schemas.CardArt
import com.velvet.domain.BaseCardInteractor
import com.velvet.domain.mappers.FromCardArtToString
import com.velvet.domain.mappers.FromCardToCardItem
import com.velvet.domain.mappers.FromCardToDetails
import com.velvet.domain.states.CardDetails
import com.velvet.domain.states.CardItem
import com.velvet.domain.usecases.CardArtUseCase
import com.velvet.domain.usecases.CardDetailsUseCase
import com.velvet.domain.usecases.CardItemsUseCase
import org.koin.dsl.binds
import org.koin.dsl.module

val domainModule = module {

    single<Mapper<CardArt, String>> {
        FromCardArtToString()
    }

    single<Mapper<Card, CardDetails>> {
        FromCardToDetails()
    }

    single<Mapper<Card, CardItem>> {
        FromCardToCardItem()
    }

    single {

    }

    single {
        BaseCardInteractor(get(), get(), get(), get())
    }.binds(arrayOf(CardArtUseCase::class, CardDetailsUseCase::class, CardItemsUseCase::class))
}