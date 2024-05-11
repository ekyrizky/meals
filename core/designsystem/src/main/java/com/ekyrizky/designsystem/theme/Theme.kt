package com.ekyrizky.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier

@Composable
fun MealsTheme(
    colors: MealsColors = MealsColors.defaultColors(),
    typography: MealsTypography = MealsTypography.defaultTypography(),
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography
    ) {
        Box(
            modifier = Modifier.background(colors.background)
        ) {
            content()
        }
    }
}

object MealsTheme {
    val colors: MealsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: MealsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}