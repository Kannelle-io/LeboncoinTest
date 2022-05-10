package com.test.leboncointest

import android.app.Application
import com.test.leboncointest.di.apiModule
import com.test.leboncointest.di.repoModule
import com.test.leboncointest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                apiModule,
                repoModule,
                viewModelModule))
        }
    }
}