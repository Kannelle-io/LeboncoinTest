package com.test.leboncointest.utils.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("show")
    fun show(view: View, show: Boolean?) {
        if (show != null && show) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }


}