package com.android.cleanarchitectureproject.navigation

sealed class Screen(val route: String){
    object CocktailListScreen: Screen("cocktail_list")
    object CocktailDetailScreen: Screen("cocktail_detail")
}
