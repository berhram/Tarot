package com.velvet.tarot.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.domain.CardUI
import com.velvet.domain.states.CardItemState
import com.velvet.tarot.R

data class CardListState(
    val onRefresh: () -> Unit = { },
    val cards: List<CardItemState> = emptyList(),
    val isLoading: Boolean = true
)

@Composable
fun CardList(modifier: Modifier, state: CardListState) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
            onRefresh = state.onRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                if (state.cards.isEmpty()) {
                    items(items = listOf(System.currentTimeMillis()), key = { it }) {
                        Column(
                            modifier = Modifier
                                .fillParentMaxHeight()
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = stringResource(id = R.string.no_cards)
                            )
                        }
                    }
                } else {
                    items(state.cards) { CardItem(it) }
                }
            }
        }
    }
}

