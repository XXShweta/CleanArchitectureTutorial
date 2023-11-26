package com.android.cleanarchitectureproject.domain.use_case.get_cocktails

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.data.remote.dto.toCocktail
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCocktailUseCase @Inject constructor(
    private val repository: CocktailRepository
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(id: String): Flow<Resource<Cocktail>> = flow{
        try {
            emit(Resource.Loading())
            val cocktail = repository.getCocktailById(id).drinks[0].toCocktail()
            emit(Resource.Success(cocktail))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error("Check your Internet Connection!"))
        }
    }

}
