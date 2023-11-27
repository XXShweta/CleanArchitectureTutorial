package com.android.cleanarchitectureproject.presentation.cocktail_detail

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.android.cleanarchitectureproject.common.ProcessAPI
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.common.component.ImageViewComposable
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.presentation.cocktail_detail.components.CocktailDescription


@Composable
fun CocktailDetailScreen(
    state: Resource<Cocktail>,
    modifier: Modifier = Modifier
) {
    ProcessAPI(state) {
        Box(
            modifier = modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            state.data?.let { cocktail ->
                Card(
                    shape = RoundedCornerShape(size = 12.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 10.dp, horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            ImageViewComposable(
                                imageUrl = cocktail.url,
                                modifier = Modifier
                                    .size(300.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, MaterialTheme.colorScheme.surface, CircleShape)
                            )
                        }
                        item {
                            CocktailDescription(cocktail.name)
                        }

                        cocktail.iba?.let {iba->
                            item { CocktailDescription(iba) }
                        }
                        cocktail.alcoholicCategory?.let {
                           item {   CocktailDescription(it)}
                        }
                        cocktail.glassCategory?.let {
                            item {  CocktailDescription(it)}
                        }
                        cocktail.instructions?.let {
                            item { CocktailDescription(it)}
                        }
                    }
                }
            }
        }
    }

}
