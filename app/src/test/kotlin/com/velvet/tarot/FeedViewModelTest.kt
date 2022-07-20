package com.velvet.tarot

import com.velvet.data.card.CardFilter
import com.velvet.domain.usecase.FetchCardsUseCase
import com.velvet.domain.usecase.FilterCardsUseCase
import com.velvet.domain.usecase.GetAllCardsUseCase
import com.velvet.domain.usecase.SearchCardsUseCase
import com.velvet.tarot.feed.FeedEffect
import com.velvet.tarot.feed.FeedState
import com.velvet.tarot.feed.FeedViewModel
import io.mockk.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.orbitmvi.orbit.test

class FeedViewModelTest {
    private val fetchCardsUseCase = mockk<FetchCardsUseCase>()
    private val getAllCardsUseCase = mockk<GetAllCardsUseCase>()
    private val filterCardsUseCase = mockk<FilterCardsUseCase>()
    private val searchCardsUseCase = mockk<SearchCardsUseCase>()

    @Test
    fun whenFilterMajorClicked_shouldFilterCards() {
        //given
        coEvery { getAllCardsUseCase.invoke() } returns listOf(
            Card(
                name = "Unknown",
                art = "xxx",
                type = CardTypes.MAJOR,
                meaningUp = "Some meaning up 1",
                meaningRev = "Some meaning rev 1",
                description = "such a fun card!"),
            Card(
                type = CardTypes.MINOR,
                name = "Some name 1",
                art = "xxx",
                meaningUp = "Some meaning up 2",
                meaningRev = "Some meaning rev 2",
                description = "such a bad card!"),
            Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))
        coEvery { fetchCardsUseCase.invoke() } just runs
        coEvery { filterCardsUseCase.invoke(isMajorEnabled = any(), isMinorEnabled = any()) } returns listOf(Card(
            type = CardTypes.MINOR,
            name = "Some name 1",
            art = "xxx",
            meaningUp = "Some meaning up 2",
            meaningRev = "Some meaning rev 2",
            description = "such a bad card!"))
        val viewModel = FeedViewModel(fetchCardsUseCase = fetchCardsUseCase, getAllCardsUseCase = getAllCardsUseCase, searchCardsUseCase = searchCardsUseCase, filterCardsUseCase = filterCardsUseCase).test(FeedState())

        //when
        runBlocking { viewModel.testIntent { setFilter(CardTypes.MAJOR) } }

        //then
        viewModel.assert(FeedState()) {
            states(
                { copy(filter = CardFilter(isMajorEnabled = false, isMinorEnabled = true)) },
                { copy(cards = listOf(Card(
                    type = CardTypes.MINOR,
                    name = "Some name 1",
                    art = "xxx",
                    meaningUp = "Some meaning up 2",
                    meaningRev = "Some meaning rev 2",
                    description = "such a bad card!"))) }
            )
        }

        coVerify(exactly = 1) { getAllCardsUseCase.invoke() }
        coVerify(exactly = 1) { filterCardsUseCase.invoke(isMinorEnabled = any(), isMajorEnabled = any())   }
        coVerify(exactly = 0) { searchCardsUseCase.invoke(any())  }
        coVerify(exactly = 1) { fetchCardsUseCase.invoke()  }
    }

    @Test
    fun whenFilterMinorClicked_shouldFilterCards() {
        //given
        coEvery { getAllCardsUseCase.invoke() } returns listOf(
            Card(
                name = "Unknown",
                art = "xxx",
                type = CardTypes.MAJOR,
                meaningUp = "Some meaning up 1",
                meaningRev = "Some meaning rev 1",
                description = "such a fun card!"),
            Card(
                type = CardTypes.MINOR,
                name = "Some name 1",
                art = "xxx",
                meaningUp = "Some meaning up 2",
                meaningRev = "Some meaning rev 2",
                description = "such a bad card!"),
            Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))
        coEvery { fetchCardsUseCase.invoke() } just runs
        coEvery { filterCardsUseCase.invoke(isMajorEnabled = any(), isMinorEnabled = any()) } returns listOf(Card(
            name = "Unknown",
            art = "xxx",
            type = CardTypes.MAJOR,
            meaningUp = "Some meaning up 1",
            meaningRev = "Some meaning rev 1",
            description = "such a fun card!"))
        val viewModel = FeedViewModel(fetchCardsUseCase = fetchCardsUseCase, getAllCardsUseCase = getAllCardsUseCase, searchCardsUseCase = searchCardsUseCase, filterCardsUseCase = filterCardsUseCase).test(FeedState())

        //when
        runBlocking { viewModel.testIntent { setFilter(CardTypes.MINOR) } }

        //then
        viewModel.assert(FeedState()) {
            states(
                { copy(filter = CardFilter(isMajorEnabled = true, isMinorEnabled = false)) },
                { copy(cards = listOf(Card(
                    name = "Unknown",
                    art = "xxx",
                    type = CardTypes.MAJOR,
                    meaningUp = "Some meaning up 1",
                    meaningRev = "Some meaning rev 1",
                    description = "such a fun card!"))) }
            )
        }
        coVerify(exactly = 1) { getAllCardsUseCase.invoke() }
        coVerify(exactly = 1) { filterCardsUseCase.invoke(isMinorEnabled = any(), isMajorEnabled = any())   }
        coVerify(exactly = 0) { searchCardsUseCase.invoke(any())  }
        coVerify(exactly = 1) { fetchCardsUseCase.invoke()  }
    }

    //TODO suspicious behaviour: cards val null
    @Test
    fun whenRefresh_stateShouldBeUpdated() {
        //given
        coEvery { fetchCardsUseCase.invoke() } just runs
        coEvery { getAllCardsUseCase.invoke() } returns listOf(
            Card(
                name = "Unknown",
                art = "xxx",
                type = CardTypes.MAJOR,
                meaningUp = "Some meaning up 1",
                meaningRev = "Some meaning rev 1",
                description = "such a fun card!"),
            Card(
                type = CardTypes.MINOR,
                name = "Some name 1",
                art = "xxx",
                meaningUp = "Some meaning up 2",
                meaningRev = "Some meaning rev 2",
                description = "such a bad card!"),
            Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))
        val viewModel = FeedViewModel(fetchCardsUseCase = fetchCardsUseCase, getAllCardsUseCase = getAllCardsUseCase, searchCardsUseCase = searchCardsUseCase, filterCardsUseCase = filterCardsUseCase).test(FeedState())

        //when
        runBlocking { viewModel.testIntent { refresh() } }

        //then
        viewModel.assert(FeedState()) {
            states(
                { copy(isLoading = true) },
                { copy(filter = CardFilter(isMajorEnabled = true, isMinorEnabled = true), isLoading = false, cards = listOf(
                    Card(
                        name = "Unknown",
                        art = "xxx",
                        type = CardTypes.MAJOR,
                        meaningUp = "Some meaning up 1",
                        meaningRev = "Some meaning rev 1",
                        description = "such a fun card!"),
                    Card(
                        type = CardTypes.MINOR,
                        name = "Some name 1",
                        art = "xxx",
                        meaningUp = "Some meaning up 2",
                        meaningRev = "Some meaning rev 2",
                        description = "such a bad card!"),
                    Card(
                        type = CardTypes.NONE,
                        name = "Some name 2",
                        art = "xxx",
                        meaningUp = "",
                        meaningRev = "all be as bad as it can be!",
                        description = "Some desc"))) }
            )
        }
        coVerify(exactly = 2) { getAllCardsUseCase.invoke() }
        coVerify(exactly = 0) { filterCardsUseCase.invoke(isMinorEnabled = any(), isMajorEnabled = any())   }
        coVerify(exactly = 0) { searchCardsUseCase.invoke(any())  }
        coVerify(exactly = 2) { fetchCardsUseCase.invoke()  }
    }

    @Test
    fun whenFilterClick_shouldExpand() {
        //given
        coEvery { fetchCardsUseCase.invoke() } just runs
        coEvery { getAllCardsUseCase.invoke() } returns listOf(
            Card(
                name = "Unknown",
                art = "xxx",
                type = CardTypes.MAJOR,
                meaningUp = "Some meaning up 1",
                meaningRev = "Some meaning rev 1",
                description = "such a fun card!"),
            Card(
                type = CardTypes.MINOR,
                name = "Some name 1",
                art = "xxx",
                meaningUp = "Some meaning up 2",
                meaningRev = "Some meaning rev 2",
                description = "such a bad card!"),
            Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))
        val viewModel = FeedViewModel(fetchCardsUseCase = fetchCardsUseCase, getAllCardsUseCase = getAllCardsUseCase, searchCardsUseCase = searchCardsUseCase, filterCardsUseCase = filterCardsUseCase).test(FeedState())

        //when
        runBlocking { viewModel.testIntent { filterClick() } }

        //then
        viewModel.assert(FeedState()) { states({ copy(isExpanded = true) }) }
        coVerify(exactly = 1) { getAllCardsUseCase.invoke() }
        coVerify(exactly = 0) { filterCardsUseCase.invoke(isMinorEnabled = any(), isMajorEnabled = any())   }
        coVerify(exactly = 0) { searchCardsUseCase.invoke(any())  }
        coVerify(exactly = 1) { fetchCardsUseCase.invoke()  }
    }

    @Test
    fun whenShowCardTriggered_shouldPostSideEffect_withGivenCardName() {
        //given
        coEvery { fetchCardsUseCase.invoke() } just runs
        coEvery { getAllCardsUseCase.invoke() } returns listOf(
            Card(
                name = "Unknown",
                art = "xxx",
                type = CardTypes.MAJOR,
                meaningUp = "Some meaning up 1",
                meaningRev = "Some meaning rev 1",
                description = "such a fun card!"),
            Card(
                type = CardTypes.MINOR,
                name = "Some name 1",
                art = "xxx",
                meaningUp = "Some meaning up 2",
                meaningRev = "Some meaning rev 2",
                description = "such a bad card!"),
            Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))
        val viewModel = FeedViewModel(fetchCardsUseCase = fetchCardsUseCase, getAllCardsUseCase = getAllCardsUseCase, searchCardsUseCase = searchCardsUseCase, filterCardsUseCase = filterCardsUseCase).test(FeedState())

        //when
        runBlocking { viewModel.testIntent { showCard("some card") } }

        //then
        viewModel.assert(FeedState()) {
            postedSideEffects(FeedEffect.ShowCard("some card")) }
        coVerify(exactly = 1) { getAllCardsUseCase.invoke() }
        coVerify(exactly = 0) { filterCardsUseCase.invoke(isMinorEnabled = any(), isMajorEnabled = any())   }
        coVerify(exactly = 0) { searchCardsUseCase.invoke(any())  }
        coVerify(exactly = 1) { fetchCardsUseCase.invoke()  }
    }

    @Test
    fun whenSearch_shouldGiveCards() {
        //given
        coEvery { fetchCardsUseCase.invoke() } just runs
        coEvery { getAllCardsUseCase.invoke() } returns listOf(
            Card(
                name = "Unknown",
                art = "xxx",
                type = CardTypes.MAJOR,
                meaningUp = "Some meaning up 1",
                meaningRev = "Some meaning rev 1",
                description = "such a fun card!"),
            Card(
                type = CardTypes.MINOR,
                name = "Some name 1",
                art = "xxx",
                meaningUp = "Some meaning up 2",
                meaningRev = "Some meaning rev 2",
                description = "such a bad card!"),
            Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))
        coEvery { searchCardsUseCase.invoke("Some name 2") } returns listOf(Card(
            type = CardTypes.NONE,
            name = "Some name 2",
            art = "xxx",
            meaningUp = "",
            meaningRev = "all be as bad as it can be!",
            description = "Some desc"))
        val viewModel = FeedViewModel(fetchCardsUseCase = fetchCardsUseCase, getAllCardsUseCase = getAllCardsUseCase, searchCardsUseCase = searchCardsUseCase, filterCardsUseCase = filterCardsUseCase).test(FeedState())

        //when
        runBlocking { viewModel.testIntent { searchCard("Some name 2") } }
        runBlocking { delay(2000) }
        //then
        viewModel.assert(FeedState()) { states(
            { copy(searchText = "Some name 2") },
            { copy(cards = listOf(Card(
                type = CardTypes.NONE,
                name = "Some name 2",
                art = "xxx",
                meaningUp = "",
                meaningRev = "all be as bad as it can be!",
                description = "Some desc"))) }
        ) }
        coVerify(exactly = 1) { getAllCardsUseCase.invoke() }
        coVerify(exactly = 0) { filterCardsUseCase.invoke(isMinorEnabled = any(), isMajorEnabled = any())   }
        coVerify(exactly = 1) { searchCardsUseCase.invoke(any())  }
        coVerify(exactly = 1) { fetchCardsUseCase.invoke()  }
    }
}