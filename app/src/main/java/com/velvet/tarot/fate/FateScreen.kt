package com.velvet.tarot.fate

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.velvet.tarot.theme.AppTheme

@Composable
fun FateScreen(viewModel: FateViewModel) {
    val state = viewModel.container.stateFlow.collectAsState()
    ConstraintLayout(Modifier.fillMaxSize().background(AppTheme.colors.background)) {
        val (card, revealButtonBox) = createRefs()
        Column(Modifier.constrainAs(card) {
            top.linkTo(parent.top)
            bottom.linkTo(revealButtonBox.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
        }, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = state.value.card.art,
                style = AppTheme.typography.body1,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textPrimary
            )
            Column(Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()) {
                Text(
                    text = stringResource(id = com.velvet.tarot.R.string.type)+ " " + state.value.card.type,
                    style = AppTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary
                )
                Text(
                    text = stringResource(id = com.velvet.tarot.R.string.name)+ " " + state.value.card.name,
                    style = AppTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary
                )
                Text(
                    text = stringResource(id = com.velvet.tarot.R.string.meaning)+ " " + state.value.card.meaning,
                    style = AppTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary
                )
                Text(
                    text = stringResource(id = com.velvet.tarot.R.string.desc)+ " " + state.value.card.description,
                    style = AppTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary
                )
            }

        }
        Box(
            Modifier
                .padding(10.dp)
                .constrainAs(revealButtonBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                }) {
            Button(
                onClick = { viewModel.getCard() },
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.background, contentColor = AppTheme.colors.textPrimary)
            ) {
                Text(
                    text = stringResource(id = com.velvet.tarot.R.string.reveal),
                    style = AppTheme.typography.body1
                )
            }
        }

    }
}