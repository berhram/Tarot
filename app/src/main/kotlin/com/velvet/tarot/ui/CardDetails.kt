package com.velvet.tarot.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.velvet.domain.states.CardDetailsState
import com.velvet.tarot.R

@Composable
fun CardDetails(state: CardDetailsState) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = state.cardUI.art,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.type) + " " + state.cardUI.arcana,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.name) + " " + state.cardUI.name,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.meaning_up) + " " + state.cardUI.meaningUp,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.meaning_rev) + " " + state.cardUI.meaningRev,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.desc) + " " + state.cardUI.description,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground
        )
    }
}