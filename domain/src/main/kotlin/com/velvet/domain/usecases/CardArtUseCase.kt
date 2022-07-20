package com.velvet.domain.usecases

interface CardArtUseCase {

    suspend fun art(id: String): String
}