package com.velvet.domain.usecases

interface CardArtUseCase {

    suspend fun art(name: String): String
}