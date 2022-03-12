package com.velvet.tarot

import com.velvet.mvi.MviViewModel

class MainViewModel : MviViewModel<MainContract.View, MainScreenState, MainScreenEffect>(),
    MainContract.ViewModel {

}