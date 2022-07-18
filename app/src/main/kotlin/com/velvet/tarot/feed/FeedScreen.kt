package com.velvet.tarot.feed

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velvet.data.card.Card
import com.velvet.data.card.CardTypes
import com.velvet.tarot.R
import com.velvet.tarot.ui.AppTheme
import com.velvet.tarot.ui.CardItem
import com.velvet.tarot.ui.SearchBar
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
        TopAppBar(backgroundColor = AppTheme.colors.background, elevation = 0.dp, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.tarot_dir),
                    style = AppTheme.typography.h1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DropdownMenu(
                        expanded = state.isExpanded,
                        onDismissRequest = { viewModel.filterClick() },
                        modifier = Modifier.background(AppTheme.colors.background)
                    ) {
                        DropdownMenuItem(onClick = { viewModel.setFilter(CardTypes.MAJOR) }) {
                            val check = if (state.filter.isMajorEnabled) "[X] - " else "[ ] - "
                            Text(
                                text = check + stringResource(id = R.string.major),
                                style = AppTheme.typography.body1,
                                textAlign = TextAlign.Start,
                                color = AppTheme.colors.textPrimary
                            )
                        }
                        DropdownMenuItem(onClick = { viewModel.setFilter(CardTypes.MINOR) }) {
                            val check = if (state.filter.isMinorEnabled) "[X] - " else "[ ] - "
                            Text(
                                text = check + stringResource(id = R.string.major),
                                style = AppTheme.typography.body1,
                                textAlign = TextAlign.Start,
                                color = AppTheme.colors.textPrimary
                            )
                        }
                    }
                    IconButton(
                        onClick = { viewModel.filterClick() },
                        modifier = if (state.filter.isEnable()) Modifier.background(AppTheme.colors.effect) else Modifier.background(
                            AppTheme.colors.background
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter), contentDescription = stringResource(
                                id = R.string.button_filter
                            ), tint = AppTheme.colors.textPrimary
                        )
                    }
                }
            }
        }
    }, backgroundColor = AppTheme.colors.background) {

    }
}