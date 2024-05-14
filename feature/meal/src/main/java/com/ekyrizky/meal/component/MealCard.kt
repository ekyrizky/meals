package com.ekyrizky.meal.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekyrizky.designsystem.theme.MealsTheme
import com.ekyrizky.model.Meal

@Composable
fun MealCard(
    meal: Meal,
    modifier: Modifier = Modifier,
    index: Int,
    onClick: (String) -> Unit
) {

    val isEven = index % 2 == 0

    Row(
        horizontalArrangement = if (isEven) Arrangement.Start else Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = if (isEven) MealsTheme.colors.primary else MealsTheme.colors.secondary
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .background(MealsTheme.colors.tertiary)
            .clickable { onClick(meal.id) },
    ) {

        if (isEven) {
            AsyncImage(
                model = meal.image,
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .padding(10.dp)
                    .clip(CircleShape)
            )
        }

        Text(
            text = meal.name,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            style = MealsTheme.typography.titleMedium,
            color = if (isEven) MealsTheme.colors.primary else MealsTheme.colors.secondary,
            textAlign = if (isEven) TextAlign.Start else TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (!isEven) {
            AsyncImage(
                model = meal.image,
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .padding(10.dp)
                    .clip(CircleShape)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun MealCardPreview() {
//    Box(modifier = Modifier.fillMaxSize()) {
//        MealCard(
////            item = Meal()
//        )
//    }
//}