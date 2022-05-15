package com.test.leboncointest.ui.albumlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.leboncointest.data.api.domain.AlbumRepository
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.provider.TestAlbumProvider
import com.test.leboncointest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.*
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class AlbumListViewModelTest : KoinTest {

    private val albumListViewModel: AlbumListViewModel by inject()

    private lateinit var mockModule: Module
    private lateinit var mockedAlbumRepository: AlbumRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockedAlbumRepository = mock(AlbumRepository::class.java)
        mockModule = module {
            single { mockedAlbumRepository }
        }

        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun fetchAlbums_success() {
        val expectedAlbums = TestAlbumProvider.createAlbum(5)
        val flow = flow {
            emit(Resource.Loading())
            emit(Resource.Success(expectedAlbums))
        }
        `when`(mockedAlbumRepository.fetchAlbums()).thenReturn(flow)
        albumListViewModel.fetchAlbums()
        val albums = albumListViewModel.albums.value
        val isLoading = albumListViewModel.isLoading.value
        val error = albumListViewModel.hasError.value
        assertEquals(albums, expectedAlbums)
        assertEquals(isLoading, false)
        assertEquals(error, false)
    }

    @Test
    fun fetchAlbums_loading() {
        val flow: Flow<Resource<List<Album>>> = flow {
            emit(Resource.Loading())
        }
        `when`(mockedAlbumRepository.fetchAlbums()).thenReturn(flow)
        albumListViewModel.fetchAlbums()
        val albums = albumListViewModel.albums.value
        val isLoading = albumListViewModel.isLoading.value
        val error = albumListViewModel.hasError.value
        assertEquals(albums, null)
        assertEquals(isLoading, true)
        assertEquals(error, false)
    }

    @Test
    fun fetchAlbums_error() {
        val flow: Flow<Resource<List<Album>>> = flow {
            emit(Resource.Error("Error"))
        }
        `when`(mockedAlbumRepository.fetchAlbums()).thenReturn(flow)
        albumListViewModel.fetchAlbums()
        val albums = albumListViewModel.albums.value
        val isLoading = albumListViewModel.isLoading.value
        val error = albumListViewModel.hasError.value
        assertEquals(albums, null)
        assertEquals(isLoading, false)
        assertEquals(error, true)
    }
}