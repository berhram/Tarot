package com.velvet.tarot.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) {
        darkColors(
            primary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFFFFFFFF),
            onPrimary = Color(0xFF000000),
            secondary = Color(0xFFFFFFFF),
            secondaryVariant = Color(0xFFFFFFFF),
            onSecondary = Color(0xFF000000),
            surface = Color(0xFF000000),
            onSurface = Color(0xFFFFFFFF),
            background = Color(0xFF000000),
            onBackground = Color(0xFFFFFFFF),
            error = Color(0xFFDA0000),
            onError = Color(0xFFFFFFFF)
        )
    } else {
        lightColors(
            primary = Color(0xFF000000),
            primaryVariant = Color(0xFF000000),
            onPrimary = Color(0xFFFFFFFF),
            secondary = Color(0xFF000000),
            secondaryVariant = Color(0xFF000000),
            onSecondary = Color(0xFFFFFFFF),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000),
            background = Color(0xFFFFFFFF),
            onBackground = Color(0xFF000000),
            error = Color(0xFFDA0000),
            onError = Color(0xFF000000)
        )
    }
    CompositionLocalProvider(LocalDimensions provides Dimensions(), LocalTypography provides AppTypography()) {
        MaterialTheme(
            colors = colors,
            content = content
        )
    }
}