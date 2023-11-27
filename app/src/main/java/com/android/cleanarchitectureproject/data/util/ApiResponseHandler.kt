package com.android.cleanarchitectureproject.data.util

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
                Resource.Error("Response body is null")
            }
        } else {
            Resource.Error(response.message() ?: "Unexpected Error")
        }
    }catch (e: HttpException) {
        Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred")
    }catch (e: IOException){
        Resource.Error(message = "Couldn't reach server. Check your internet connection.")
    }catch (e: Exception) {
        Resource.Error("Exception: ${e.message ?: "Unexpected Error"}")
    }
}

