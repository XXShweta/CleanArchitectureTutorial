package com.android.cleanarchitectureproject.presentation.cocktail_detail

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.cleanarchitectureproject.common.ProcessAPI
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.model.Cocktail


@Composable
fun CocktailDetailScreen(state: Resource<Cocktail>) {
    ProcessAPI(
        state
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            state.data?.let {cocktail ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${cocktail.name}. ${cocktail.iba} (${cocktail.alcoholicCategory})",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.weight(8f)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = cocktail.instructions?: "",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = cocktail.tag.toString(),
                            style = MaterialTheme.typography.titleSmall
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = cocktail.glassCategory.toString(),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }

}
