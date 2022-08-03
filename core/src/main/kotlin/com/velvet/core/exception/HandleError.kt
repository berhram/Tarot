package com.velvet.core.exception

import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface HandleError {

    fun handle(error: Exception): Exception

    class Base : HandleError {

        override fun handle(error: Exception) = when (error) {
            is UnknownHostException, is SocketTimeoutException -> NoInternetConnectionException()
            else -> ServiceUnavailableException()
        }
    }
}