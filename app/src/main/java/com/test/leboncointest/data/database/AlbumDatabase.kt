package com.test.leboncointest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.leboncointest.data.database.dao.AlbumDao
import com.test.leboncointest.data.models.Album


@Database(entities = arrayOf(Album::class), version = 1, exportSchema = false)
public abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {

        @Volatile
        private var INSTANCE: AlbumDatabase? = null

        fun getDatabase(context: Context): AlbumDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDatabase::class.java,
                    "album_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}