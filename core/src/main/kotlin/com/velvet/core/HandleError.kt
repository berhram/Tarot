package com.velvet.core

interface HandleError {

    fun handle(error: Exception): Exception
}