package com.ekyrizky.designsystem.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ekyrizky.designsystem.R
import com.ekyrizky.designsystem.icon.MealsIcon
import com.ekyrizky.designsystem.theme.MealsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MealsTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (navigationIcon != null)
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = null
                    )
                }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MealsTheme.colors.primary,
            titleContentColor = MealsTheme.colors.onPrimary,
            navigationIconContentColor = MealsTheme.colors.onPrimary
        ),
        modifier = modifier.testTag("niaTopAppBar"),
    )
}

@Preview
@Composable
private fun MealsTopAppBarPreview() {
    MealsTheme {
        MealsTopAppBar(stringResource(id = R.string.app_name))
    }
}

@Preview
@Composable
private fun MealsTopAppBarNavigationPreview() {
    MealsTheme {
        MealsTopAppBar(
            title = stringResource(id = R.string.app_name),
            navigationIcon = MealsIcon.ArrowBack
        )
    }
}