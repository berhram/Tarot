package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import com.velvet.models.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: Repository) : ContainerHost<FeedState, FeedEffect>, ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(FeedState(isLoading = true, cards = emptyList()))

    init {
        refresh()
    }

    fun refresh() = intent {
        val cards = repository.getCards()
        reduce {
            state.copy(isLoading = false, cards = cards)
        }
    }
}