package com.velvet.domain

interface SpecificCardUseCase {

    fun cardById(id: String): CardUI
}