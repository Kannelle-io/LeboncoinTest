package com.test.leboncointest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)