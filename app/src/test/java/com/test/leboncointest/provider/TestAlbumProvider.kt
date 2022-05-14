package com.test.leboncointest.provider

import androidx.annotation.VisibleForTesting
import com.test.leboncointest.data.models.Album
import java.util.*

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object TestAlbumProvider {

    val album = Album(
        albumId = 0,
        title = "title",
        url = "url",
        thumbnailUrl = "thumbnailUrl"
    )

    fun createAlbum(count: Int): List<Album> {
        val albums = mutableListOf<Album>()
        for (i in 0..count) {
            val album = Album(
                albumId = i,
                title = "title$i",
                url = "url$i",
                thumbnailUrl = "thumbnailUrl$i"
            )
            albums.add(album)
        }
        return albums
    }

}