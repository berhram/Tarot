package com.velvet.models.local.arts

interface CardArtStore {
    fun getArt(name: String) : String
}