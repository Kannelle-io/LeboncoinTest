package com.test.leboncointest.data.repositories

import com.test.leboncointest.data.api.domain.AlbumRepository
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.data.api.services.AlbumServiceProvider
import com.test.leboncointest.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AlbumRepositoryImpl(private val albumServiceProvider: AlbumServiceProvider) :
    AlbumRepository {

    override fun fetchAlbums() = flow<Resource<List<Album>>> {
        emit(Resource.Loading())

        val albumService = albumServiceProvider.getAlbumService()

        try {
            val albumDto = albumService.getAlbums()
            val albums = albumDto.map { it.toAlbum() }
            emit(Resource.Success(albums))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }

    }.flowOn(Dispatchers.IO)

}