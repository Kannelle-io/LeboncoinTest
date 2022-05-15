package com.test.leboncointest.data.api.domain

import com.test.leboncointest.data.models.Album
import com.test.leboncointest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun fetchAlbums(): Flow<Resource<List<Album>>>
}