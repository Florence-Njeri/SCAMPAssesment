package com.example.scampassesment

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.scampassesment.di.databaseModule
import com.example.scampassesment.di.repositoryModule
import com.example.scampassesment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, repositoryModule, databaseModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}