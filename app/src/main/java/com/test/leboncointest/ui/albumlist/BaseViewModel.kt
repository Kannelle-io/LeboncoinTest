package com.test.leboncointest.ui.albumlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {

    val isLoading: LiveData<Boolean>
        get() = _isLoading
    internal val _isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val hasError: LiveData<Boolean>
        get() = _hasError
    internal val _hasError: MutableLiveData<Boolean> = MutableLiveData()
}