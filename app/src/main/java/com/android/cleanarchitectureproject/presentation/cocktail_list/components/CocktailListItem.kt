package com.android.cleanarchitectureproject.presentation.cocktail_list.components

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import com.android.cleanarchitectureproject.domain.model.Cocktail
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.cleanarchitectureproject.common.component.ImageViewComposable

@Composable
fun CocktailListItem(
    cocktail: Cocktail,
    onItemClick: (Cocktail) -> Unit
){
    Card(
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(cocktail) }
                .padding(vertical = 10.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageViewComposable(
                imageUrl = cocktail.url,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.surface, CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = cocktail.name,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
            )
        }
    }
}
