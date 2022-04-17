package com.velvet.tarot.feed

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.data.card.Card
import com.velvet.data.card.CardTypes
import com.velvet.tarot.R
import com.velvet.tarot.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeedScreen(viewModel: FeedViewModel, onShowCard: (cardName: String) -> Unit) {
    val state = viewModel.container.stateFlow.collectAsState().value
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is FeedEffect.ShowCard -> onShowCard(it.cardName)
            }
        }
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = AppTheme.colors.background, elevation = 0.dp, modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(id = R.string.tarot_dir),
                    style = AppTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DropdownMenu(expanded = state.isExpanded, onDismissRequest = { viewModel.filterClick() }, modifier = Modifier.background(AppTheme.colors.background)) {
                        DropdownMenuItem(onClick = { viewModel.setFilter(CardTypes.MAJOR) }) {
                            val check = if (state.filter.isMajorEnabled) "[X] - " else "[ ] - "
                            Text(text = check + stringResource(id = R.string.major),
                                style = AppTheme.typography.body1,
                                textAlign = TextAlign.Start,
                                color = AppTheme.colors.textPrimary)
                        }
                        DropdownMenuItem(onClick = { viewModel.setFilter(CardTypes.MINOR) }) {
                            val check = if (state.filter.isMinorEnabled) "[X] - " else "[ ] - "
                            Text(text = check + stringResource(id = R.string.major),
                                style = AppTheme.typography.body1,
                                textAlign = TextAlign.Start,
                                color = AppTheme.colors.textPrimary)
                        }
                    }
                    IconButton(onClick = { viewModel.filterClick() },
                        modifier = if (state.filter.isEnable()) Modifier.background(AppTheme.colors.effect) else Modifier.background(AppTheme.colors.background)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter), contentDescription = stringResource(
                                id = R.string.button_filter
                            ), tint = AppTheme.colors.textPrimary
                        )
                    }
                }
            }
        }
    }, backgroundColor = AppTheme.colors.background) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            SearchBar(searchText = state.searchText, onChangedSearchText = { viewModel.searchCard(it) })
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                onRefresh = { viewModel.refresh() },
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    if (state.cards.isNullOrEmpty()) {
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
                            items = state.cards,
                            key = { it.name }
                        ) { CardItem(it, viewModel = viewModel) }
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(card: Card, viewModel: FeedViewModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { viewModel.showCard(card.name) }) {
        Text(
            text = card.name,
            style = AppTheme.typography.h1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
    }
}

@Composable
fun SearchBar(
    searchText: String,
    onChangedSearchText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = searchText,
            textStyle = AppTheme.typography.body1,
            onValueChange = onChangedSearchText,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppTheme.colors.effect,
                unfocusedBorderColor = AppTheme.colors.primary,
                textColor = AppTheme.colors.textPrimary,
                cursorColor = AppTheme.colors.effect
            ),
            label = { Text(text =  stringResource(R.string.search), color = AppTheme.colors.textPrimary, style = AppTheme.typography.subtitle) }
        )
    }
}

