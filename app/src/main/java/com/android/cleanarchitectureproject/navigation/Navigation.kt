package com.android.cleanarchitectureproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.cleanarchitectureproject.common.Constants.PARAM_COCKTAIL_ID
import com.android.cleanarchitectureproject.presentation.cocktail_list.CocktailList
import com.android.cleanarchitectureproject.presentation.cocktail_detail.CocktailDetailScreen
import com.android.cleanarchitectureproject.presentation.cocktail_detail.CocktailDetailViewModel
import com.android.cleanarchitectureproject.presentation.cocktail_list.CocktailListViewModel


@Composable
fun Navigation(navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.CocktailListScreen.route
    ) {
        composable(
            route = Screen.CocktailListScreen.route
        ) {
            val viewModel: CocktailListViewModel = hiltViewModel()
            CocktailList(
                viewModel.state.collectAsState().value,
                modifier
            ){
                navController.navigateToDetail(it)
            }
        }
        composable(
            route = Screen.CocktailDetailScreen.route + "/{${PARAM_COCKTAIL_ID}}"
        ) {
            val viewModel: CocktailDetailViewModel = hiltViewModel()
            CocktailDetailScreen(
                viewModel.state.collectAsState().value,
                modifier
            )
        }
    }
}

fun NavController.navigateToDetail(cocktailId: String){
    this.navigate(Screen.CocktailDetailScreen.route + "/${cocktailId}")
}
