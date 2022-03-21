package com.velvet.tarot.feed

import androidx.lifecycle.ViewModel
import com.velvet.models.cache.Cache
import com.velvet.models.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: Repository, private val cache: Cache) : ContainerHost<FeedState, FeedEffect>, ViewModel() {
    override val container: Container<FeedState, FeedEffect> = container(FeedState(
        isLoading = true,
        cards = emptyList(),
        isInitial = true,
        scrollPosition = cache.getSavedPosition(),
        scrollOffset = cache.getSavedOffset(),
        isScrollNeeded = true))

    fun refresh() = intent {
        reduce {
            state.copy(
                isLoading = true,
                isInitial = false
            )
        }
        repository.getCards()
        val cards = cache.retrieveCardsAfterCall()
        reduce {
            state.copy(
                isLoading = false,
                cards = cards
            )
        }
    }

    fun initialRefresh() = intent {
        if (cache.isCacheEmpty()) {
            refresh()
        } else {
            val cards = cache.retrieveCards()
            reduce {
                state.copy(
                    cards = cards,
                    isLoading =  false
                )
            }
        }
    }

    fun saveStateAndOffset(position: Int, offset: Int) = intent {
        cache.savePosition(position = position)
        cache.savedOffset(offset = offset)
    }

    fun scrollComplete() = intent {
        reduce {
            state.copy(isScrollNeeded = false)
        }
    }
}