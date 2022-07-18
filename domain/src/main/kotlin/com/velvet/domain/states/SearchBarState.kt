package com.velvet.domain.states

import androidx.compose.ui.Modifier

data class SearchBarState(
    val searchText: String = "",
    val onChangedSearchText: (String) -> Unit = { },
    val modifier: Modifier = Modifier
)