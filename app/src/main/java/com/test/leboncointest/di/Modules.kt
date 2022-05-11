package com.test.leboncointest.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.leboncointest.data.api.domain.AlbumRepository
import com.test.leboncointest.data.api.network.NetworkServiceFactory
import com.test.leboncointest.data.api.network.ServiceFactory
import com.test.leboncointest.data.repositories.AlbumRepositoryImpl
import com.test.leboncointest.data.api.services.AlbumServiceProvider
import com.test.leboncointest.data.api.services.AlbumServiceProviderImpl
import com.test.leboncointest.data.database.AlbumDatabase
import com.test.leboncointest.data.database.dao.AlbumDao
import com.test.leboncointest.ui.albumlist.AlbumDetailViewModel
import com.test.leboncointest.ui.albumlist.AlbumListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

val databaseModule = module {

    fun provideDatabase(application: Application): AlbumDatabase {
        return AlbumDatabase.getDatabase(application)
    }

    fun provideAlbumsDao(database: AlbumDatabase): AlbumDao {
        return database.albumDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideAlbumsDao(get()) }
}

val repoModule = module {
    single {
        val albumRepository: AlbumRepository = AlbumRepositoryImpl(get(), get())
        albumRepository
    }
}

val viewModelModule = module {
    viewModel {
        AlbumListViewModel(get())
    }

    viewModel {
        AlbumDetailViewModel()
    }
}
