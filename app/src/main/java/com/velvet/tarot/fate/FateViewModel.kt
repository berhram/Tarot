package com.velvet.tarot.fate

import com.velvet.mvi.MviViewModel

class FateViewModel : MviViewModel<FateContract.View, FateScreenState, FateScreenEffect>(),
    FateContract.ViewModel {

}