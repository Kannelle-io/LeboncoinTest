package com.test.leboncointest.data.api.network

import okhttp3.Interceptor
import retrofit2.Converter

class NetworkServiceFactory(
    converterFactory: Converter.Factory
) : ServiceFactory(converterFactory) {

    override fun interceptors(): List<Interceptor> {

        val accessToken: String? = null

        val headers = mutableListOf<NetworkRequestHeader>()

        accessToken?.let {
            val authHeader = NetworkRequestHeader(
                type = "Authorization",
                value = "Bearer $accessToken"
            )
            headers.add(authHeader)
        }

        return arrayListOf(
            NetworkRequestInterceptor(
                headers
            )
        )
    }
}