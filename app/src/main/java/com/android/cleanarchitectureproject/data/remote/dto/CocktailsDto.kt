package com.android.cleanarchitectureproject.data.remote.dto

import com.android.cleanarchitectureproject.domain.model.Cocktail

data class CocktailsDto(
    val drinks: List<Drink>
)

data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strTags: String? = null,
    val strCategory: String?= null,
    val strIBA: String?= null,
    val strAlcoholic: String? = null,
    val strGlass: String?= null,
    val strInstructions: String? = null
)

fun Drink.toCocktail(): Cocktail {
    return Cocktail(
        id = idDrink,
        name = strDrink,
        url = strDrinkThumb,
        tag = strTags,
        category = strCategory,
        iba = strIBA,
        alcoholicCategory = strAlcoholic,
        glassCategory = strGlass,
        instructions = strInstructions
    )
}

