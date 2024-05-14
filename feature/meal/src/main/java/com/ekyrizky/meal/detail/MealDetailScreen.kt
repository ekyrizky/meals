package com.ekyrizky.meal.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ekyrizky.designsystem.R
import com.ekyrizky.designsystem.component.CircularProgress
import com.ekyrizky.designsystem.component.MealsTopAppBar
import com.ekyrizky.designsystem.icon.MealsIcon
import com.ekyrizky.designsystem.theme.MealsTheme
import com.ekyrizky.navigation.currentComposeNavigator

@Composable
fun MealDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: MealDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MealDetailScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
private fun MealDetailScreen(
    uiState: MealDetailUiState,
    modifier: Modifier = Modifier
) {

    val composeNavigator = currentComposeNavigator

    when (uiState) {
        is MealDetailUiState.Error -> {
            Text(
                text = uiState.message ?: "Unknown Error",
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is MealDetailUiState.Loading -> CircularProgress()

        is MealDetailUiState.Success -> {
            Scaffold(
                topBar = {
                    MealsTopAppBar(
                        title = stringResource(id = R.string.app_name),
                        navigationIcon = MealsIcon.ArrowBack,
                        onNavigationClick = { composeNavigator.navigateUp() }
                    )
                }
            ) { contentPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = uiState.data.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1 / 1f)
                            .clip(RoundedCornerShape(32.dp))
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiState.data.name,
                        style = MealsTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}