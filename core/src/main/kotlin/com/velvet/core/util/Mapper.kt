package com.velvet.core.util

interface Mapper<T, V> {

    fun map(data: T): V
}