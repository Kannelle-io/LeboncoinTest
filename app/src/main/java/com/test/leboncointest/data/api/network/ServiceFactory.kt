package com.test.leboncointest.data.api.network

import com.test.leboncointest.constants.ApiConstants.BASE_URL
import com.test.leboncointest.constants.ApiConstants.TIME_OUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

abstract class ServiceFactory(
    private val converterFactory: Converter.Factory
) {

    fun <T> create(serviceType: Class<T>): T {
        return create(
            serviceType,
            getHttpClient(),
            converterFactory
        )
    }

    private fun <T> create(
        serviceType: Class<T>,
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): T {
        val retrofit = getNetAdapter(client, converterFactory)
        return retrofit.create(serviceType)
    }

    private fun getNetAdapter(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        val builder = Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
        return builder.build()
    }

    private fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptors().forEach { interceptor -> builder.addInterceptor(interceptor) }
        builder
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        return builder.build()
    }

    abstract fun interceptors(): List<Interceptor>
}