package com.test.leboncointest.utils.databinding

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewBindingAdapterTest {

    private val context by lazy {
        ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun show_with_true_returnVisible() {
        val imageView = ImageView(context)
        val show = true
        ViewBindingAdapter.show(imageView, show)
        assertEquals(imageView.visibility, View.VISIBLE)
    }

    @Test
    fun show_with_false_returnGone() {
        val imageView = ImageView(context)
        val show = false
        ViewBindingAdapter.show(imageView, show)
        assertEquals(imageView.visibility, View.GONE)
    }
}