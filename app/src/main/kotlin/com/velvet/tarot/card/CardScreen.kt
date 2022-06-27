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
import com.velvet.data.card.Card
import com.velvet.data.card.CardTypes
import com.velvet.tarot.R
import com.velvet.tarot.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CardScreen(viewModel: CardViewModel, onBack: () -> Unit) {
    val state = viewModel.container.stateFlow.collectAsState().value
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                is CardEffect.GoBack -> onBack()
            }
        }
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = AppTheme.colors.background, elevation = 0.dp, modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = state.card?.let { it.name.lowercase().replace(" ", "_") + stringResource(
                    id = R.string.card_dir) } ?: stringResource(id = R.string.unknown),
                    style = AppTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary)
                IconButton(onClick = { viewModel.goBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_q), contentDescription = stringResource(
                            id = R.string.button_back
                        ), tint = AppTheme.colors.textPrimary
                    )
                }
            }
        }
    }, backgroundColor = AppTheme.colors.background) {
        if (state.card == null) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(id = R.string.loading),
                    style = AppTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.textPrimary
                )
            }
        } else {
            ShowCard(card = state.card)
        }
    }
}

@Composable
fun ShowCard(card: Card) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = card.art,
            style = AppTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textPrimary, modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.type) + " " + when (card.type) {
                CardTypes.MAJOR -> stringResource(id = R.string.major)
                CardTypes.MINOR -> stringResource(id = R.string.minor)
                else -> stringResource(id = R.string.unknown)
            },
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.name) + " " + card.name.ifEmpty { stringResource(id = R.string.unknown) },
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.meaning_up) + " " + card.meaningUp.ifEmpty { stringResource(id = R.string.unknown) },
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.meaning_rev) + " " + card.meaningRev.ifEmpty { stringResource(id = R.string.unknown) },
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.desc) + " " + card.description.ifEmpty { stringResource(id = R.string.unknown) },
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
    }
}