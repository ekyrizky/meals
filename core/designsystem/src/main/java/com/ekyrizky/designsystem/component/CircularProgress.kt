package com.ekyrizky.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ekyrizky.designsystem.theme.MealsTheme

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        color = MealsTheme.colors.primary,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
private fun CircularProgressPreview() {
    MealsTheme {
        CircularProgress()
    }
}