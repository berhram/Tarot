package com.velvet.tarot.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class CardItemState(
    val title: String,
    val onClick: (String) -> Unit
)

@Composable
fun CardItem(state: CardItemState) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { state.onClick(state.title) }) {
        Text(
            text = state.title,
            style = MaterialTheme.appTypography.title,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onSurface
        )
    }
}