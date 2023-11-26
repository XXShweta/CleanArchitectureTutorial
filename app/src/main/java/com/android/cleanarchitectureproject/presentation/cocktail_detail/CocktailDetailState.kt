package com.android.cleanarchitectureproject.presentation.cocktail_detail

import com.android.cleanarchitectureproject.domain.model.Cocktail

data class CocktailDetailState(
    val isLoading: Boolean = false,
    val cocktail: Cocktail? = null,
    val error: String = ""
)
