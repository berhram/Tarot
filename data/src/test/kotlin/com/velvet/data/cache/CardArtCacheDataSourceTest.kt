package com.velvet.data.cache

import com.velvet.data.schemas.CardArt
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class CardArtCacheDataSourceTest {

    @Test
    fun `success art by id`() = runBlocking {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals(false, cardArtCacheDataSource.art("someId").isEmpty())
    }

    @Test
    fun `failed (empty) art by id`() = runBlocking {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals(true, cardArtCacheDataSource.art("abrakadabra").isEmpty())
    }

    @Test
    fun `success default art`() = runBlocking {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals(CardArt("default", "defaultArt"), cardArtCacheDataSource.defaultArt())
    }

    private class TestCards : ReadCardArts {

        override fun read(): List<CardArt> = listOf(CardArt("someId", "someArt"))
    }

    private class TestDefaultArt : ReadDefaultArt {

        override fun read(): CardArt = CardArt("default", "defaultArt")
    }
}