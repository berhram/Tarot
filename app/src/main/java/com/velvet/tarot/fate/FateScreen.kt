package com.velvet.tarot.fate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.velvet.models.card.CardDetailsScheme
import com.velvet.tarot.R

@Composable
fun FateScreen(viewModel: FateViewModel) {
    val state = viewModel.container.stateFlow.collectAsState()
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (buttons, cards, revealButtonBox) = createRefs()
        Row(
            Modifier
                .padding(10.dp)
                .constrainAs(buttons) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
            Button(onClick = { viewModel.getCards(state.value.layoutType) }) {
                Text(text = "One card")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = { viewModel.getCards(state.value.layoutType) }) {
                Text(text = "Two cards")
            }
        }
        Box(Modifier.constrainAs(cards) {
            top.linkTo(buttons.bottom)
            bottom.linkTo(revealButtonBox.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            if (state.value.layoutType == GuessingTypes.THREE) {
                ThreeCard(cards = state.value.cards[state.value.layoutType])
            } else if (state.value.layoutType == GuessingTypes.ONE) {
                OneCard(cards = state.value.cards[state.value.layoutType])
            }
        }
        Box(
            Modifier
                .padding(10.dp)
                .constrainAs(revealButtonBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            Button(onClick = { /*TODO*/ }) {
                
            }
        }

    }
}

@Composable
fun OneCard(cards: List<CardDetailsScheme>?) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            if (cards == emptyList<CardDetailsScheme>()) {
                Card(id = "tarot_back_pale")
            } else {
                val name = cards?.get(0)?.nameShort
                if (name != null) {
                    Card(id = name)
                }
            }
        }
    }
}

@Composable
fun ThreeCard(cards: List<CardDetailsScheme>?) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            if (cards == null) {
                Card(id = "tarot_back_pale")
            } else {
                val name = cards[0].nameShort
                if (name != null) {
                    Card(id = name)
                }
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (cards == null) {
                Card(id = "tarot_back_pale")
            } else {
                val name = cards[1].nameShort
                if (name != null) {
                    Card(id = name)
                }
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (cards == null) {
                Card(id = "tarot_back_pale")
            } else {
                val name = cards[2].nameShort
                if (name != null) {
                    Card(id = name)
                }
            }
        }
    }
}

@Composable
fun Cardholder() {
    Image(painter = painterResource(id = R.drawable.tarot_back_pale), contentDescription = "Cardholder")
}

@Composable
fun Card(id: String) {
    val context = LocalContext.current
    val drawableId = remember(id) {
        var out = context.resources.getIdentifier(
            id,
            "drawable",
            context.packageName
        )
        if (out == 0) {
            out = context.resources.getIdentifier(
                "no_such_card",
                "drawable",
                context.packageName
            )
        }
        out
    }
    Box(
        Modifier.padding(10.dp)) {
        Image(painter = painterResource(id = drawableId), contentDescription = "Card", contentScale = ContentScale.Fit)
    }
}