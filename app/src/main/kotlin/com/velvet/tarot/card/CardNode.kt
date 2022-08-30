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
import com.velvet.tarot.R
import com.velvet.tarot.ui.AutoSizeText
import com.velvet.tarot.ui.appTypography
import com.velvet.tarot.ui.dimensions
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class CardNode(
    buildContext: BuildContext,
    private val cardId: String,
    private val onBack: () -> Unit
) :
    Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CardViewModel = getViewModel(parameters = { parametersOf(cardId) })
        val state = viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                is CardScreenEffect.GoBack -> onBack()
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
                        text = "~/tarot/" + state.value.cardDetails.id + ".card",
                        lines = 1,
                        style = MaterialTheme.appTypography.title,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    IconButton(onClick = { viewModel.goBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_q),
                            contentDescription = stringResource(
                                id = R.string.button_back
                            ),
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }, backgroundColor = MaterialTheme.colors.background) {
            if (state.value.isNoSuchCard) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MaterialTheme.dimensions.medium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_such_card),
                        style = MaterialTheme.appTypography.bodyBold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            } else {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = MaterialTheme.dimensions.medium),
                    horizontalAlignment = Alignment.Start
                ) {
                    AutoSizeText(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = state.value.cardDetails.art,
                        lines = 20,
                        style = MaterialTheme.appTypography.title,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.type) + " " + state.value.cardDetails.type,
                        style = MaterialTheme.appTypography.body,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.name) + " " + state.value.cardDetails.name,
                        style = MaterialTheme.appTypography.body,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.meaning_up) + " " + state.value.cardDetails.meaningUp,
                        style = MaterialTheme.appTypography.body,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.meaning_rev) + " " + state.value.cardDetails.meaningRev,
                        style = MaterialTheme.appTypography.body,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.desc) + " " + state.value.cardDetails.description,
                        style = MaterialTheme.appTypography.body,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}