package com.adel.data.network

import com.google.gson.Gson
import com.adel.data.model.error.ApiError
import io.hecpay.util.CustomResponse
import retrofit2.Response
import java.net.HttpURLConnection


suspend fun <T> getResult(call: suspend () -> Response<T>): CustomResponse<T> {
    try {
        val response = call()
        if (response.code() == HttpURLConnection.HTTP_OK ) {
            val body = response.body()
            return CustomResponse.Success<T>(body!!)
        } else {
            val gson = Gson()
            val apiError: ApiError =
                gson.fromJson(response.errorBody()!!.charStream(), ApiError::class.java)
            return CustomResponse.Error(apiError.detail.toString())
        }
    } catch (e: java.lang.Exception) {
        return CustomResponse.Fail()

    }

}




