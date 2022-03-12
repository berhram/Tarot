package com.velvet.tarot

import com.velvet.mvi.HostedFragment

class MainFragment : HostedFragment<
        MainContract.View,
        MainScreenState,
        MainScreenEffect,
        MainContract.ViewModel,
        MainContract.Host>(),
    MainContract.View  {

    override fun createModel(): MainContract.ViewModel {
        TODO("Not yet implemented")
    }
}

@Composable
f