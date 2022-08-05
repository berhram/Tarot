package com.velvet.tarot.feed

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.tarot.R
import com.velvet.tarot.ui.appTypography
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeedScreen(viewModel: FeedViewModel, onShowCard: (cardName: String) -> Unit) {
    val state = viewModel.container.stateFlow.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                FeedEffect.ErrorRefresh -> Toast.makeText(context, R.string.error_refresh, Toast.LENGTH_LONG).show()
                is FeedEffect.ShowCard -> onShowCard(it.cardName)
            }
        }
    }
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.tarot_dir),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onBackground
                )
                IconButton(
                    onClick = { viewModel.toggleSearch() },
                    modifier = if (state.isSearchExpanded) Modifier.background(MaterialTheme.colors.error) else Modifier.background(
                        MaterialTheme.colors.background
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_q), contentDescription = stringResource(
                            id = R.string.search
                        ), tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }, backgroundColor = MaterialTheme.colors.background) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                onRefresh = { viewModel.searchCards("") },
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
                        items(state.cards) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clickable { viewModel.showCard(it.name) }) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.appTypography.title,
                                    textAlign = TextAlign.Start,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}