package com.velvet.tarot.fate

import android.graphics.Paint
import android.os.Bundle
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    OneCard(id = "wapa")
                    OneCard(id = "wakn")
                    OneCard(id = "waki")
                }
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "get one card")
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "get two card")
                    }
                }

            }
        }
    }
}

@Composable
fun OneCard(id: String) {
    Card(id = id)
}

@Preview
@Composable
fun Cardholder() {
    Image(painter = painterResource(id = R.drawable.tarot_back_pale), contentDescription = "Cardholder")
}

@Composable
fun Card(id: String) {
    val context = LocalContext.current
    val drawableId = remember(id) {
        context.resources.getIdentifier(
            id,
            "drawable",
            context.packageName
        )
    }
    Image(painter = painterResource(id = drawableId), contentDescription = "Card", contentScale = ContentScale.Fit)
}