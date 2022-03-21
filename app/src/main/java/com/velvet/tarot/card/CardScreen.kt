package com.velvet.tarot.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.velvet.data.card.Card
import com.velvet.tarot.R
import com.velvet.tarot.theme.AppTheme

@Composable
fun CardScreen(viewModel: CardViewModel, onBack: () -> Unit) {
    val state = viewModel.container.stateFlow.collectAsState()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = state.value.card.name,
                style = AppTheme.typography.h1,
                textAlign = TextAlign.Start, color = AppTheme.colors.textPrimary)},
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.button_back), tint = AppTheme.colors.textPrimary
                    )
                }
            }, backgroundColor = AppTheme.colors.background
        )
    }, backgroundColor = AppTheme.colors.background) {
        ShowCard(card = state.value.card)
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
            text = stringResource(id = R.string.type) + " " + card.type,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.name) + " " + card.name,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.meaning_up) + " " + card.meaningUp,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.meaning_rev) + " " + card.meaningRev,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
        Text(
            text = stringResource(id = R.string.desc) + " " + card.description,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
            color = AppTheme.colors.textPrimary
        )
    }
}