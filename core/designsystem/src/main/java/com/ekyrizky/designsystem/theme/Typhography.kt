package com.ekyrizky.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class MealsTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle
) {
    companion object {
        @Composable
        fun defaultTypography(): MealsTypography = MealsTypography(
            titleLarge = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            ),
            titleMedium = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                letterSpacing = 0.1.sp,
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
            bodyMedium = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
            )
        )
    }
}

internal val LocalTypography = compositionLocalOf<MealsTypography> {
    error("No typography provided! Make sure to wrap all usages in MealsTheme.")
}