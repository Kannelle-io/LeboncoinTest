package com.test.leboncointest.data.api.services

import com.test.leboncointest.data.models.dto.AlbumDto
import retrofit2.http.GET

interface AlbumService {
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumDto>
}