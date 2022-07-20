package com.velvet.domain

interface Mapper<T, V> {

    fun map(data: T): V
}