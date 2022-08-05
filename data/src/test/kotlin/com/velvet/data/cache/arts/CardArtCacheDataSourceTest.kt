package com.velvet.data.cache.arts

import com.velvet.core.Read
import com.velvet.data.schemas.CardArt
import org.junit.Assert
import org.junit.Test

internal class CardArtCacheDataSourceTest {

    @Test
    fun `success art by id`() {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals("someArt", cardArtCacheDataSource.art("someId"))
    }

    @Test
    fun `failed art by id`() {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals("defaultArt", cardArtCacheDataSource.art("abrakadabra"))
    }

    @Test
    fun `success default art`() {
        val cardArtCacheDataSource = CardArtCacheDataSource.Base(TestCards(), TestDefaultArt())
        Assert.assertEquals("defaultArt", cardArtCacheDataSource.defaultArt())
    }

    private class TestCards : Read<List<CardArt>> {

        override fun read(): List<CardArt> = listOf(CardArt("someId", "someArt"))
    }

    private class TestDefaultArt : Read<CardArt> {

        override fun read(): CardArt = CardArt("default", "defaultArt")
    }
}