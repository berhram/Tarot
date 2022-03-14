package com.velvet.tarot.fate

import android.content.res.Resources
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.viewModels
import com.velvet.mvi.HostedFragment
import com.velvet.tarot.R

class FateFragment : HostedFragment<
        FateContract.View,
        FateScreenState,
        FateScreenEffect,
        FateContract.ViewModel,
        FateContract.Host>(),
    FateContract.View {

    private val screenState = mutableStateOf(FateScreenUiState())

    override fun createModel(): FateContract.ViewModel {
        val model: FateViewModel by viewModels()
        return model
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = ComposeView(requireContext())
        view.setContent { FateScreen(screenState, model) }
        return view
    }
}

@Composable
fun FateScreen(
    screenState: MutableState<FateScreenUiState>,
    model: FateContract.ViewModel?
) {
    MaterialTheme() {
        Scaffold() {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (buttons, cards) = createRefs()
                Box(modifier = Modifier.constrainAs(cards) {
                    top.linkTo(parent.top)
                    bottom.linkTo(buttons.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    ThreeCard(first = "waki", second = "cuki", third = "ar16")
                }
                Row(
                    Modifier
                        .padding(10.dp)
                        .constrainAs(buttons) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
                    Button(onClick = { model.getCards(1) }) {
                        Text(text = "One card")
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Two cards")
                    }
                }
            }
        }
    }
}

@Composable
fun OneCard(id: String) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            Card(id = id)
        }
    }
}

@Composable
fun ThreeCard(first: String, second: String, third: String) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            Card(id = first)
        }
        Box(modifier = Modifier.weight(1f)) {
            Card(id = second)
        }
        Box(modifier = Modifier.weight(1f)) {
            Card(id = third)
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