package com.test.leboncointest.data.database.dao

import androidx.room.*
import com.test.leboncointest.data.models.Album
import java.nio.file.Files.delete


@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    fun getAlbums(): List<Album>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(album: Album): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(albums: List<Album>)

    @Query("DELETE FROM album")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(albums: List<Album>) {
        deleteAll()
        insertAll(albums)
    }
}