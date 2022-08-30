package com.velvet.tarot.feed

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.velvet.tarot.R
import com.velvet.tarot.ui.appTypography
import com.velvet.tarot.ui.dimensions
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class FeedNode(
    buildContext: BuildContext,
    private val onShowCard: (cardName: String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: FeedViewModel = getViewModel()
        val state = viewModel.collectAsState()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                FeedEffect.ErrorRefresh -> Toast.makeText(
                    context,
                    R.string.error_refresh,
                    Toast.LENGTH_LONG
                ).show()
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
                            .background(if (state.value.isSearchExpanded) MaterialTheme.colors.onBackground else MaterialTheme.colors.background)
                            .clickable { if (!state.value.isLoading) viewModel.toggleSearch() },
                        style = MaterialTheme.appTypography.title,
                        color = if (state.value.isSearchExpanded) MaterialTheme.colors.background else MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = ":v",
                        modifier = Modifier
                            .background(if (state.value.isSimpleList) MaterialTheme.colors.onBackground else MaterialTheme.colors.background)
                            .clickable { if (!state.value.isLoading) viewModel.switchView() },
                        style = MaterialTheme.appTypography.title,
                        color = if (state.value.isSimpleList) MaterialTheme.colors.background else MaterialTheme.colors.onBackground
                    )
                }
            }
        }, backgroundColor = MaterialTheme.colors.background) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
            ) {
                val (search, loading, noCards, serviceUnavailable, noInternet, simpleCards, cards) = createRefs()
                Column(modifier = Modifier
                    .padding(MaterialTheme.dimensions.small)
                    .constrainAs(search) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }) {
                    AnimatedVisibility(visible = state.value.isSearchExpanded) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.value.searchText,
                            textStyle = MaterialTheme.appTypography.body,
                            onValueChange = { viewModel.searchCards(it) })
                    }
                }
                Column(modifier = Modifier.constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    AnimatedVisibility(
                        visible = state.value.isLoading,
                        enter = fadeIn(animationSpec = keyframes {
                            durationMillis = 500
                        }),
                        exit = fadeOut(animationSpec = keyframes {
                            durationMillis = 500
                        })
                    ) {
                        Text(
                            text = stringResource(id = R.string.loading),
                            style = MaterialTheme.appTypography.title,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Column(modifier = Modifier.constrainAs(serviceUnavailable) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    AnimatedVisibility(
                        visible = state.value.isServiceUnavailable,
                        enter = fadeIn(animationSpec = keyframes {
                            durationMillis = 500
                        }),
                        exit = fadeOut(animationSpec = keyframes {
                            durationMillis = 500
                        })
                    ) {
                        Text(
                            text = stringResource(id = R.string.service_unavailable),
                            style = MaterialTheme.appTypography.title,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Column(modifier = Modifier.constrainAs(noInternet) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    AnimatedVisibility(
                        visible = state.value.isNoInternetConnection,
                        enter = fadeIn(animationSpec = keyframes {
                            durationMillis = 500
                        }),
                        exit = fadeOut(animationSpec = keyframes {
                            durationMillis = 500
                        })
                    ) {
                        Text(
                            modifier = Modifier.clickable { viewModel.refresh() },
                            text = stringResource(id = R.string.no_internet_connection),
                            style = MaterialTheme.appTypography.title,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Column(modifier = Modifier.constrainAs(noCards) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    AnimatedVisibility(
                        visible = state.value.cards.list.isEmpty() && !state.value.isLoading,
                        enter = fadeIn(animationSpec = keyframes {
                            durationMillis = 500
                        }),
                        exit = fadeOut(animationSpec = keyframes {
                            durationMillis = 500
                        })
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            style = MaterialTheme.appTypography.title,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.dimensions.medium)
                        .constrainAs(simpleCards) {
                            top.linkTo(search.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }) {
                    AnimatedVisibility(
                        visible = state.value.isSimpleList && !state.value.isLoading,
                        enter = fadeIn(animationSpec = keyframes {
                            durationMillis = 1000
                        }),
                        exit = fadeOut(animationSpec = keyframes {
                            durationMillis = 500
                        })
                    ) {
                        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
                            items(state.value.cards.list) {
                                Row(
                                    Modifier
                                        .clickable { viewModel.showCard(it.id) }
                                ) {
                                    Text(
                                        text = it.name,
                                        maxLines = 1,
                                        style = MaterialTheme.appTypography.title,
                                        textAlign = TextAlign.Start,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colors.onSurface
                                    )
                                }
                            }
                        })
                    }
                }
                Column(modifier = Modifier.constrainAs(cards) {
                    top.linkTo(search.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }) {
                    AnimatedVisibility(
                        visible = !state.value.isSimpleList && !state.value.isLoading,
                        enter = fadeIn(animationSpec = keyframes {
                            durationMillis = 1000
                        }),
                        exit = fadeOut(animationSpec = keyframes {
                            durationMillis = 500
                        })
                    ) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            content = {
                                items(state.value.cards.list) {
                                    Column(
                                        Modifier
                                            .padding(MaterialTheme.dimensions.small)
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
                        )
                    }
                }
            }
        }
    }
}