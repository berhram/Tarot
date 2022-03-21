package com.velvet.tarot.feed

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.models.card.Card
import com.velvet.tarot.R
import com.velvet.tarot.navigation.Destinations
import com.velvet.tarot.theme.AppTheme

@Composable
fun FeedScreen(viewModel: FeedViewModel, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name), style = AppTheme.typography.h1,
                textAlign = TextAlign.Start, color = AppTheme.colors.textPrimary) }, backgroundColor = AppTheme.colors.background
        )
    }, backgroundColor = AppTheme.colors.background) {
        val state = viewModel.container.stateFlow.collectAsState()
        val scrollState = rememberLazyListState()
        LaunchedEffect(key1 = scrollState.isScrollInProgress) {
            if(!scrollState.isScrollInProgress) {
                viewModel.saveStateAndOffset(position = scrollState.firstVisibleItemIndex,
                    offset = scrollState.firstVisibleItemScrollOffset)
            }
        }
        LaunchedEffect(key1 = state.value.isInitial) {
            viewModel.initialRefresh()
        }
        LaunchedEffect(key1 = state.value.isScrollNeeded) {
            scrollState.scrollToItem(
                index = state.value.scrollPosition,
                scrollOffset = state.value.scrollOffset
            )
            viewModel.scrollComplete()
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.value.isLoading),
            onRefresh = { viewModel.refresh() },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(state = scrollState) {
                if (state.value.cards.isNullOrEmpty()) {
                    items(items = listOf(System.currentTimeMillis()), key = { it }) {
                        Column(
                            modifier = Modifier
                                .fillParentMaxHeight()
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = stringResource(id = R.string.no_cards),
                            )
                        }
                    }
                } else {
                    items(
                        items = state.value.cards,
                        key = { it.name }
                    ) { CardItem(it, navController) }
                }
            }
        }
    }
}

@Composable
fun CardItem(card: Card, navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navController.navigate("${Destinations.CARDS}/${card.name}") }) {
        Text(
            text = card.name,
            style = AppTheme.typography.h1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
    }
}

