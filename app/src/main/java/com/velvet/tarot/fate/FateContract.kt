package com.velvet.tarot.fate

import com.velvet.mvi.FragmentContract

class FateContract {
    interface ViewModel : FragmentContract.ViewModel<FateScreenState, FateScreenEffect> {

    }

    interface View : FragmentContract.View {

    }

    interface Host : FragmentContract.Host {

    }
}