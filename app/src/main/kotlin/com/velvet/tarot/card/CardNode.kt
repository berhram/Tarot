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
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.tarot.R
import com.velvet.tarot.ui.AutoSizeText
import com.velvet.tarot.ui.appTypography
import com.velvet.tarot.ui.dimensions
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

class CardNode(buildContext: BuildContext, private val cardId: String, private val onBack: () -> Unit) :
    Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CardViewModel = getViewModel(parameters = { parametersOf(cardId) })
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
                    AutoSizeText(
                        text = "~/tarot/" + state.cardDetails.id + ".card",
                        lines = 1,
                        style = MaterialTheme.appTypography.title,
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
                SwipeRefresh(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MaterialTheme.dimensions.medium),
                    state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                    onRefresh = { viewModel.refresh() }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (state.isLoading) {
                            Text(
                                text = stringResource(id = R.string.loading),
                                style = MaterialTheme.appTypography.bodyBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        if (state.isServiceUnavailable) {
                            Text(
                                text = stringResource(id = R.string.service_unavailable),
                                style = MaterialTheme.appTypography.bodyBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        if (state.isNoSuchCard) {
                            Text(
                                text = stringResource(id = R.string.no_such_card),
                                style = MaterialTheme.appTypography.bodyBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        if (state.isNoInternetConnection) {
                            Text(
                                text = stringResource(id = R.string.no_internet_connection),
                                style = MaterialTheme.appTypography.bodyBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            } else {
                SwipeRefresh(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MaterialTheme.dimensions.medium),
                    state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                    onRefresh = { viewModel.refresh() }
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.Start
                    ) {
                        AutoSizeText(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = state.cardDetails.art,
                            lines = 20,
                            style = MaterialTheme.appTypography.title,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = stringResource(id = R.string.type) + " " + state.cardDetails.type,
                            style = MaterialTheme.appTypography.body,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = stringResource(id = R.string.name) + " " + state.cardDetails.name,
                            style = MaterialTheme.appTypography.body,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = stringResource(id = R.string.meaning_up) + " " + state.cardDetails.meaningUp,
                            style = MaterialTheme.appTypography.body,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = stringResource(id = R.string.meaning_rev) + " " + state.cardDetails.meaningRev,
                            style = MaterialTheme.appTypography.body,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = stringResource(id = R.string.desc) + " " + state.cardDetails.description,
                            style = MaterialTheme.appTypography.body,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    }
}