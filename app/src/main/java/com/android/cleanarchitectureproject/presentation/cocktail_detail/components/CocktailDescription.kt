package com.android.cleanarchitectureproject.presentation.cocktail_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.cleanarchitectureproject.presentation.ui.theme.AppDimensionsTheme


@Composable
fun CocktailDescription (text: String){
    Spacer(modifier = Modifier.height(AppDimensionsTheme.dimensions.twelveDp))
    Text(
        text = text,
        fontSize = AppDimensionsTheme.dimensions.sixteenSp,
        color = MaterialTheme.colorScheme.secondary,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
    )
}
