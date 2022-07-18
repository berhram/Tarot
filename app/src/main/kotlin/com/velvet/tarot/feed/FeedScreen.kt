package com.velvet.tarot.feed

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velvet.tarot.R
import com.velvet.tarot.ui.CardList
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeedScreen(viewModel: FeedViewModel, onShowCard: (cardName: String) -> Unit) {
    val state = viewModel.container.stateFlow.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                FeedEffect.ErrorRefresh -> Toast.makeText(context, R.string.error_refresh, Toast.LENGTH_LONG).show()
                is FeedEffect.ShowCard -> onShowCard(it.cardName)
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
                Text(
                    text = stringResource(id = R.string.tarot_dir),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }, backgroundColor = MaterialTheme.colors.background) {
        CardList(modifier = Modifier.fillMaxSize(), state = state.cards)
    }
}