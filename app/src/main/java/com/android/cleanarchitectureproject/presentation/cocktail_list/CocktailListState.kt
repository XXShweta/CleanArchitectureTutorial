package com.android.cleanarchitectureproject.presentation.cocktail_list

import com.android.cleanarchitectureproject.domain.model.Cocktail

data class CocktailListState(
    val isLoading: Boolean = false,
    val cocktails: List<Cocktail> = emptyList(),
    val error: String = ""
)
