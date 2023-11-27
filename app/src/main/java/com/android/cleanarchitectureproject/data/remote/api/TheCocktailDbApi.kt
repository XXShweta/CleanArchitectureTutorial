package com.android.cleanarchitectureproject.data.remote.api

import com.android.cleanarchitectureproject.data.remote.dto.CocktailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCocktailDbApi {

    @GET("v1/1/filter.php?c=Cocktail")
    suspend fun getCocktails(): Response<CocktailsDto>

    @GET("v1/1/lookup.php")
    suspend fun getCocktailById(@Query("i") id: String): Response<CocktailsDto>

}
