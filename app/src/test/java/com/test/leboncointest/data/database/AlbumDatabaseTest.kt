package com.test.leboncointest.data.database


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.leboncointest.data.database.dao.AlbumDao
import com.test.leboncointest.provider.TestAlbumProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest : KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val albumDao: AlbumDao by inject()

    @Test
    fun insert() = runBlocking(Dispatchers.Default) {
        val album = TestAlbumProvider.album
        album.id = albumDao.insert(album)
        val albums = albumDao.getAlbums()

        Assert.assertTrue(albums.contains(album))
    }

}