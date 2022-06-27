package com.velvet.tarot.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.velvet.tarot.R

val font = FontFamily(
    Font(R.font.classic_console)
)

data class AppTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    val body2: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }


