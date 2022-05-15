package com.test.leboncointest.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.test.leboncointest.data.api.domain.AlbumRepository
import com.test.leboncointest.data.api.network.ServiceFactory
import com.test.leboncointest.data.api.services.AlbumService
import com.test.leboncointest.data.api.services.AlbumServiceProvider
import com.test.leboncointest.data.database.dao.AlbumDao
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.provider.TestAlbumDtoProvider
import com.test.leboncointest.provider.TestAlbumProvider
import com.test.leboncointest.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull

@RunWith(AndroidJUnit4::class)
class AlbumRepositoryImplTest: KoinTest {

    private val albumRepository: AlbumRepository by inject()
    private lateinit var mockModule: Module

    private lateinit var mockedAlbumService: AlbumServiceProvider
    private lateinit var serviceFactory: ServiceFactory
    private lateinit var albumService: AlbumService
    private lateinit var albumDao: AlbumDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockedAlbumService = Mockito.mock(AlbumServiceProvider::class.java)
        serviceFactory = Mockito.mock(ServiceFactory::class.java)
        albumService = Mockito.mock(AlbumService::class.java)
        albumDao = Mockito.mock(AlbumDao::class.java)
        mockModule = module {
            single { mockedAlbumService }
            single { serviceFactory }
            single { albumService }
            single { albumDao }
        }

        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun fetchAlbums() = runBlocking {
        val albumsDto = TestAlbumDtoProvider.createAlbumDto(5)
        val exceptedAlbums = TestAlbumProvider.createAlbum(5)
        Mockito.`when`(mockedAlbumService.getAlbumService()).thenReturn(albumService)
        Mockito.`when`(albumService.getAlbums()).thenReturn(albumsDto)
        Mockito.`when`(albumDao.replaceAll(anyOrNull())).thenReturn(Unit)

        albumRepository.fetchAlbums().test {
            assertTrue(awaitItem() is Resource.Loading<List<Album>>)
            val item = awaitItem()
            assertEquals(item.data, exceptedAlbums)
            cancelAndConsumeRemainingEvents()
        }
    }

}