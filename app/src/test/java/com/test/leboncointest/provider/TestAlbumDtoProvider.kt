package com.test.leboncointest.provider

import androidx.annotation.VisibleForTesting
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.data.models.dto.AlbumDto
import java.util.*

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object TestAlbumDtoProvider {

    fun createAlbumDto(count: Int): List<AlbumDto> {
        val albumsDto = mutableListOf<AlbumDto>()
        for (i in 0..count) {
            val albumDto = AlbumDto(
                albumId = i,
                title = "title$i",
                url = "url$i",
                thumbnailUrl = "thumbnailUrl$i"
            )
            albumsDto.add(albumDto)
        }
        return albumsDto
    }

}