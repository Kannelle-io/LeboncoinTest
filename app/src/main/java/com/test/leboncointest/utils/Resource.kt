package com.test.leboncointest.utils

sealed class Resource<T>(val data: T? = null, val code: Int? = null,  val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, code: Int? = null, data: T? = null): Resource<T>(data, code, message)
}