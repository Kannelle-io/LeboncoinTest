package com.test.leboncointest.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "album")
@Parcelize
data class Album(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable