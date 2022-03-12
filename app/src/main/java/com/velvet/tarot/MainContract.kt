package com.velvet.tarot

import com.velvet.mvi.FragmentContract

class MainContract {
    interface ViewModel : FragmentContract.ViewModel<MainScreenState, MainScreenEffect> {

    }

    interface View : FragmentContract.View {

    }

    interface Host : FragmentContract.Host {

    }
}