package com.velvet.tarot.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velvet.tarot.R
import com.velvet.tarot.ui.CardDetails
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
                Text(text = state.card?.let {
                    it.cardUI.name.lowercase().replace(" ", "_") + stringResource(
                        id = R.string.card_dir
                    )
                } ?: stringResource(id = R.string.unknown),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onBackground)
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
        if (state.card == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.loading),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
        } else {
            CardDetails(state.card)
        }
    }
}

