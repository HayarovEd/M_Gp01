package com.eyegym.app

import android.app.Application
import com.eyegym.app.di.baseModule
import com.eyegym.app.di.repoModule
import com.eyegym.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MGP01App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MGP01App)
            modules(
                baseModule, viewModelModule, repoModule,
            )
        }
    }
}