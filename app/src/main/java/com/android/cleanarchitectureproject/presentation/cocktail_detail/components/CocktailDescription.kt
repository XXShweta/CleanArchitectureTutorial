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


@Composable
fun CocktailDescription (text: String){
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = text,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.secondary,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
    )
}
