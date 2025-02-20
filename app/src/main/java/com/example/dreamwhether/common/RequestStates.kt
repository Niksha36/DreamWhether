package com.example.dreamwhether.common

import com.example.dreamwhether.data.remoute.dto.WhetherDTO

sealed class RequestStates<T>(val data: T? = null, val error: String? = null) {
    class Error<T>(error: String,data: T? = null) : RequestStates<T>(error = error, data = data)
    class Success<T>(data: T) : RequestStates<T>(data=data)
    class Loading<T>(data: T? = null) : RequestStates<T>(data)
}