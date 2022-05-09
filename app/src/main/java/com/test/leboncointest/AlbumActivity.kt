package com.test.leboncointest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.leboncointest.ui.albumlist.AlbumListFragment

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AlbumListFragment.newInstance())
                .commitNow()
        }
    }
}