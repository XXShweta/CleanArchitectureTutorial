package com.android.cleanarchitectureproject.domain.repository

import com.android.cleanarchitectureproject.data.remote.dto.CocktailsDto

interface CocktailRepository {

    suspend fun getCocktails(): CocktailsDto

    suspend fun getCocktailById(id: String): CocktailsDto

}
