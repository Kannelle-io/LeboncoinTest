package com.test.leboncointest.utils.databinding

import android.webkit.WebSettings
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object ImageViewViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            val image = GlideUrl(
                url, LazyHeaders.Builder()
                    .addHeader("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
                    .build()
            )
            Glide.with(imageView).load(image).into(imageView)
        }
    }

}