package com.velvet.domain.usecase

import com.velvet.data.repo.Repository

class FetchCardsUseCase(
    private val repository: Repository,
) {
    suspend operator fun invoke() {
        repository.fetch()
    }
}
