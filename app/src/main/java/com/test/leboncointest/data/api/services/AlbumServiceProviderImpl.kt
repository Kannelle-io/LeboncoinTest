package com.test.leboncointest.data.api.services

import com.test.leboncointest.data.api.network.ServiceFactory

class AlbumServiceProviderImpl(private val serviceFactory: ServiceFactory) :
    AlbumServiceProvider {

    override fun getAlbumService(): AlbumService {
        return serviceFactory.create(AlbumService::class.java)
    }
}