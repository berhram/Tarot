package com.velvet.data.cache.arts

import com.velvet.data.exception.NoSuchArtException
import com.velvet.data.schemas.CardArt
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class CardArtCacheDataSourceTest {

    @Test
    fun `success art by id`() = runBlocking {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals("someArt", cardArtCacheDataSource.art("someId"))
    }

    @Test
    fun `failed art by id`() {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertThrows(NoSuchArtException::class.java) { runBlocking { cardArtCacheDataSource.art("abrakadabra") } }
    }

    @Test
    fun `success default art`() = runBlocking {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals("defaultArt", cardArtCacheDataSource.defaultArt())
    }

    private class TestCards : ReadCardArts {

        override fun read(): List<CardArt> = listOf(CardArt("someId", "someArt"))
    }

    private class TestDefaultArt : ReadDefaultArt {

        override fun read(): CardArt = CardArt("default", "defaultArt")
    }
}