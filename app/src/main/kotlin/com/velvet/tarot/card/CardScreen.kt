package com.velvet.tarot.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.tarot.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CardScreen(viewModel: CardViewModel, onBack: () -> Unit) {
    val state = viewModel.container.stateFlow.collectAsState().value
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is CardScreenEffect.GoBack -> onBack()
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
                    text = state.cardDetails.id,
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onBackground
                )
                IconButton(onClick = { viewModel.goBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_q), contentDescription = stringResource(
                            id = R.string.button_back
                        ), tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }, backgroundColor = MaterialTheme.colors.background) {
        if (state.isLoading || state.isServiceUnavailable || state.isNoSuchCard || state.isNoInternetConnection) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SwipeRefresh(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                    onRefresh = { viewModel.refresh() }) {
                    if (state.isLoading) {
                        Text(
                            text = stringResource(id = R.string.loading),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    if (state.isServiceUnavailable) {
                        Text(
                            text = stringResource(id = R.string.service_unavailable),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    if (state.isNoSuchCard) {
                        Text(
                            text = stringResource(id = R.string.no_such_card),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    if (state.isNoInternetConnection) {
                        Text(
                            text = stringResource(id = R.string.no_internet_connection),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                SwipeRefresh(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                    onRefresh = { viewModel.refresh() }) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = state.art,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.type) + " " + state.cardDetails.type,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.name) + " " + state.cardDetails.name,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.meaning_up) + " " + state.cardDetails.meaningUp,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.meaning_rev) + " " + state.cardDetails.meaningRev,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.desc) + " " + state.cardDetails.description,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

