package com.test.leboncointest.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.leboncointest.data.api.network.NetworkServiceFactory
import com.test.leboncointest.data.api.network.ServiceFactory
import com.test.leboncointest.data.api.services.AlbumServiceProvider
import com.test.leboncointest.data.api.services.AlbumServiceProviderImpl
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        val converterFactory: Converter.Factory = MoshiConverterFactory.create(get())
        converterFactory
    }

    single { NetworkServiceFactory(get()) }

    single {
        val serviceFactory: ServiceFactory = NetworkServiceFactory(get())
        serviceFactory
    }

    single {
        val albumServiceProvider: AlbumServiceProvider = AlbumServiceProviderImpl(get())
        albumServiceProvider
    }
}

