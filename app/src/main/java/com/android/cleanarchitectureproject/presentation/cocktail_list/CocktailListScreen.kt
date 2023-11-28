package com.android.cleanarchitectureproject.presentation.cocktail_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.cleanarchitectureproject.common.ProcessAPI
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.presentation.cocktail_list.components.CocktailListItem
import com.android.cleanarchitectureproject.presentation.ui.theme.AppDimensionsTheme

@Composable
fun CocktailList(
    state: Resource<List<Cocktail>>,
    modifier: Modifier = Modifier ,
    navigateToDetail: (cocktailId: String) -> Unit
){
    ProcessAPI(state) {
        Box(modifier = modifier.fillMaxSize()) {
            state.data?.let {cocktails ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(space = AppDimensionsTheme.dimensions.twentyFourDp),
                    contentPadding = PaddingValues(all = AppDimensionsTheme.dimensions.twentyTwoDp)
                ) {
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
