package com.velvet.domain

import com.velvet.domain.usecase.FetchCardsUseCase
import com.velvet.domain.usecase.GetAllCardsUseCase
import com.velvet.domain.usecase.GetCardDetailsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FetchCardsUseCase(repository = get())
    }

    factory {
        GetCardDetailsUseCase(repository = get())
    }

    factory {
        GetAllCardsUseCase(repository = get())
    }
}