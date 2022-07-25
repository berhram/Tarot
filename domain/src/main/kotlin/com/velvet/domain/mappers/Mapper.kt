package com.velvet.domain.mappers

interface Mapper<T, V> {

    fun map(data: T): V
}