package com.adel.util


sealed class CustomResponse<out T>(val status: com.adel.util.CustomResponse.Status, val data:T?=null, val message:String?=null) {

    class Success<T>(data:T):
        com.adel.util.CustomResponse<T>(com.adel.util.CustomResponse.Status.SUCCESS, data, null)
    class Error<T>(message:String?):
        com.adel.util.CustomResponse<T>(com.adel.util.CustomResponse.Status.ERROR, null, message)
    class Fail<T>(): com.adel.util.CustomResponse<T>(com.adel.util.CustomResponse.Status.Fail)
    class Loading<T>(): com.adel.util.CustomResponse<T>(com.adel.util.CustomResponse.Status.LOADING)

    enum class Status{
        SUCCESS,
        ERROR,
        LOADING,
        Fail
    }
}