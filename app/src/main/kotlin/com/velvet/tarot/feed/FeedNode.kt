package com.velvet.tarot.feed

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.tarot.R
import com.velvet.tarot.ui.appTypography
import com.velvet.tarot.ui.dimensions
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class FeedNode(buildContext: BuildContext, private val onShowCard: (cardName: String) -> Unit) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: FeedViewModel = getViewModel()
        val state = viewModel.collectAsState()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                FeedEffect.ErrorRefresh -> Toast.makeText(context, R.string.error_refresh, Toast.LENGTH_LONG).show()
                is FeedEffect.ShowCard -> onShowCard(it.cardName)
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
                        style = MaterialTheme.appTypography.title,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = ":F",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (state.value.isSearchExpanded) MaterialTheme.colors.onBackground else MaterialTheme.colors.background)
                            .clickable { viewModel.toggleSearch() },
                        style = MaterialTheme.appTypography.title,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }, backgroundColor = MaterialTheme.colors.background) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.value.isLoading),
                onRefresh = viewModel::refresh,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.dimensions.medium)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (state.value.isLoading || state.value.isServiceUnavailable || state.value.isNoInternetConnection) {
                        if (state.value.isLoading) {
                            Text(
                                text = stringResource(id = R.string.loading),
                                style = MaterialTheme.appTypography.body,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        if (state.value.isServiceUnavailable) {
                            Text(
                                text = stringResource(id = R.string.service_unavailable),
                                style = MaterialTheme.appTypography.body,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        if (state.value.isNoInternetConnection) {
                            Text(
                                text = stringResource(id = R.string.no_internet_connection),
                                style = MaterialTheme.appTypography.body,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            content = {
                                if (state.value.cards.isEmpty() && !state.value.isLoading) {
                                    item {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
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
                                    items(state.value.cards) {
                                        Column(
                                            Modifier
                                                .padding(MaterialTheme.dimensions.medium)
                                                .clickable { viewModel.showCard(it.id) },
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = it.art,
                                                style = MaterialTheme.appTypography.caption,
                                                color = MaterialTheme.colors.onBackground
                                            )
                                            Text(
                                                text = it.name,
                                                style = MaterialTheme.appTypography.body,
                                                textAlign = TextAlign.Center,
                                                color = MaterialTheme.colors.onSurface
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}