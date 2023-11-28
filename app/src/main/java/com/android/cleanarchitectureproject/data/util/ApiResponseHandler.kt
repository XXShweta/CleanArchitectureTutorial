package com.android.cleanarchitectureproject.data.util

import com.android.cleanarchitectureproject.common.Constants.EMPTY_RESPONSE
import com.android.cleanarchitectureproject.common.Constants.SERVER_ERROR
import com.android.cleanarchitectureproject.common.Constants.UNEXPECTED_ERROR
import com.android.cleanarchitectureproject.common.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any, R : Any> handleApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): Resource<R> {
    return try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                Resource.Success(mapper.invoke(data))
            } ?: run {
                Resource.Error(EMPTY_RESPONSE)
            }
        } else {
            Resource.Error(response.message() ?: UNEXPECTED_ERROR)
        }
    }catch (e: HttpException) {
        Resource.Error(message = e.localizedMessage ?: UNEXPECTED_ERROR)
    }catch (e: IOException){
        Resource.Error(message = SERVER_ERROR)
    }catch (e: Exception) {
        Resource.Error("Exception: ${e.message ?: UNEXPECTED_ERROR}")
    }
}

