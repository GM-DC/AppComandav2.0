package com.example.appcomandav20.data

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): NetworkResult<T>(data)
    class Success<T>(data: T?): NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null): NetworkResult<T>(data, message)
}
