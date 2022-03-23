package com.velvet.tarot.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    textPrimary: Color,
    effect: Color,
    background: Color,
) {
    var primary by mutableStateOf(primary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var effect by mutableStateOf(effect)
        private set
    var background by mutableStateOf(background)
        private set

    fun copy(
        primary: Color = this.primary,
        textPrimary: Color = this.textPrimary,
        effect: Color = this.effect,
        background: Color = this.background,
    ): AppColors = AppColors(
        primary,
        textPrimary,
        effect,
        background,
    )
}

private val colorPrimary = Color(0xFFFFFFFF)
private val colorTextPrimary = Color(0xFFFFFFFF)
private val colorBackground = Color(0xFF000000)
private val colorEffect = Color(0xD3FF0000)

fun colors(
    primary: Color = colorPrimary,
    textPrimary: Color = colorTextPrimary,
    background: Color = colorBackground,
    effect: Color = colorEffect
): AppColors = AppColors(
    primary = primary,
    textPrimary = textPrimary,
    background = background,
    effect = effect
)


val LocalColors = staticCompositionLocalOf { colors() }