package com.velvet.tarot.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiSetup() {
    val systemUiController = rememberSystemUiController()
    val isLightTheme = MaterialTheme.colors.isLight
    val systemBarColor = MaterialTheme.colors.surface
    val transparentColor: (Color) -> Color = { original ->
        systemBarColor.compositeOver(original)
    }
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = systemBarColor, darkIcons = isLightTheme,
            transformColorForLightContent = transparentColor
        )
        systemUiController.setNavigationBarColor(
            color = systemBarColor,
            darkIcons = isLightTheme,
            navigationBarContrastEnforced = false,
            transformColorForLightContent = transparentColor
        )
    }
}