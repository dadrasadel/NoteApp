package io.hecpay.util


sealed class CustomResponse<out T>(val status: Status, val data:T?=null, val message:String?=null) {

    class Success<T>(data:T):CustomResponse<T>(Status.SUCCESS, data, null)
    class Error<T>(message:String?):CustomResponse<T>(Status.ERROR, null, message)
    class Fail<T>():CustomResponse<T>(Status.Fail)
    class Loading<T>():CustomResponse<T>(Status.LOADING)

    enum class Status{
        SUCCESS,
        ERROR,
        LOADING,
        Fail
    }
}