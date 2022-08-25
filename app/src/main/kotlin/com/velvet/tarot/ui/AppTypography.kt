package com.velvet.tarot.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.velvet.tarot.R

@Immutable
data class AppTypography(
    val title: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 38.sp
    ),
    val body: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    val bodyBold: TextStyle = body.copy(fontWeight = FontWeight.Bold),
    val caption: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
) {

    companion object {

        private val font = FontFamily(
            Font(R.font.classic_console)
        )
    }
}



