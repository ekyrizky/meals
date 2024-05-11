package com.ekyrizky.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MealsColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val background: Color
) {
    companion object {
        @Composable
        fun defaultColors(): MealsColors = MealsColors(
            primary = Color(0xFFEA0029),
            onPrimary = Color.White,
            secondary = Color(0xFFFFC72C),
            onSecondary = Color.White,
            background = Color(0xFFF4EBF4),
        )
    }
}

internal val LocalColors = compositionLocalOf<MealsColors> {
    error("No colors provided! Make sure to wrap all usages in MealsTheme.")
}
