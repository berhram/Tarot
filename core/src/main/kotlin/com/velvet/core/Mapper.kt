package com.velvet.core

interface Mapper<T, V> {

    fun map(data: T): V
}