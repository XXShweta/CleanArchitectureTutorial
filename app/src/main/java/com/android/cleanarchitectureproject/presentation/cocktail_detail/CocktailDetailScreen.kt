package com.android.cleanarchitectureproject.presentation.cocktail_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.cleanarchitectureproject.common.Resource


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailDetailScreen (
    viewModel: CocktailDetailViewModel= hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        when(state){
            is Resource.Error -> {
                Text(
                    text = state.message?: "Unexpected Error",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is Resource.Success -> {
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

}
