package com.android.cleanarchitectureproject.data.repository

import com.android.cleanarchitectureproject.data.remote.TheCocktailDbApi
import com.android.cleanarchitectureproject.data.remote.dto.CocktailsDto
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val api: TheCocktailDbApi
) : CocktailRepository{

    override suspend fun getCocktails(): CocktailsDto {
        return api.getCocktails()
    }

    override suspend fun getCocktailById(id: String): CocktailsDto {
        return api.getCocktailById(id)
    }


}
