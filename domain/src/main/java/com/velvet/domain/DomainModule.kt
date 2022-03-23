package com.velvet.domain

import com.velvet.domain.usecase.*
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

    factory {
        SearchCardsUseCase(repository = get())
    }

    factory {
        FilterCardsUseCase(repository = get())
    }
}