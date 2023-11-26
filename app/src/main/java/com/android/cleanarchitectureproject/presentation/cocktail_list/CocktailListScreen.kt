package com.android.cleanarchitectureproject.presentation.cocktail_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.navigation.Screen
import com.android.cleanarchitectureproject.presentation.cocktail_list.components.CocktailListItem

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailList(
    navController: NavController,
    viewModel: CocktailListViewModel = hiltViewModel()
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
                state.data?.let {cocktails ->
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(cocktails) { cocktail ->
                            CocktailListItem(
                                cocktail = cocktail,
                                onItemClick = {
                                    navController.navigate(Screen.CocktailDetailScreen.route + "/${cocktail.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}
