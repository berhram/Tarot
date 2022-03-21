package com.velvet.domain.usecase

import android.util.Log
import com.velvet.data.repo.ConfigRepository
import com.velvet.data.repo.Repository

class FetchCardsUseCase(
    private val repository: Repository,
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(): Boolean {
        Log.d("CARDS", "fetch data invoked")
        return if (!configRepository.createdDatabase) {
            repository.clear()
            repository.fetch().apply { configRepository.createdDatabase = this }
        } else {
            true
        }
    }
}
