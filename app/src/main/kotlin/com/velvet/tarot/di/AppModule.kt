package com.velvet.tarot.di

import com.velvet.tarot.card.CardViewModel
import com.velvet.tarot.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        FeedViewModel(get())
    }

    viewModel { (name: String) ->
        CardViewModel(name, get(), get())
    }
}