package com.ekyrizky.category.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekyrizky.designsystem.theme.MealsTheme
import com.ekyrizky.model.Category

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable {
                onClick(category.name)
            },
        shape = RoundedCornerShape(14.dp),
        colors = CardColors(
            containerColor = MealsTheme.colors.tertiary,
            contentColor = MealsTheme.colors.onSecondary,
            disabledContainerColor = MealsTheme.colors.tertiary,
            disabledContentColor = MealsTheme.colors.tertiary,
        ),
        border = BorderStroke(2.dp, color = MealsTheme.colors.secondary)
    ) {
        AsyncImage(
            model = category.image,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .size(150.dp)
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(12.dp),
            text = category.name,
            style = MealsTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryCardPreview() {
    MealsTheme {
        CategoryCard(
            Category(
                name = "Beef",
                image = "https://www.themealdb.com/images/category/beef.png",
                description = ""
            ),
            onClick = {}
        )
    }
}