package com.test.leboncointest.data.models.dto

import com.test.leboncointest.data.models.Album

data class AlbumDto(
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) {
    fun toAlbum(): Album {
        return Album(
            albumId = albumId,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl
        )
    }
}
