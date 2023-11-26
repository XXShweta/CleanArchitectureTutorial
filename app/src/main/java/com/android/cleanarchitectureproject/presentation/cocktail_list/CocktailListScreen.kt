package com.android.cleanarchitectureproject.presentation.cocktail_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.cleanarchitectureproject.common.ProcessAPI
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.presentation.cocktail_list.components.CocktailListItem

@Composable
fun CocktailList(
    state: Resource<List<Cocktail>>,
    navigateToDetail: (cocktailId: String) -> Unit
){
    ProcessAPI(state) {
        Box(modifier = Modifier.fillMaxSize()) {
            state.data?.let {cocktails ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(cocktails) { cocktail ->
                        CocktailListItem(
                            cocktail = cocktail,
                            onItemClick = {
                                navigateToDetail(it.id)
                            }
                        )
                    }
                }
            }
        }
    }
}
