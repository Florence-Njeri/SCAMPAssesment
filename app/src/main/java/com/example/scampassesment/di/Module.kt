package com.example.scampassesment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.scampassesment.database.CoronavirusDatabase
import com.example.scampassesment.repository.StatisticsRepository
import com.example.scampassesment.ui.viewModel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    fun provideUserRepository(
        database: CoronavirusDatabase,
        context: Context
    ): StatisticsRepository {
        return StatisticsRepository(database, context)
    }

    single { provideUserRepository(get(), get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): CoronavirusDatabase {
        return Room.databaseBuilder(application, CoronavirusDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { provideDatabase(androidApplication()) }
}

val viewModelModule = module(override = true) {
    single { MainViewModel(get()) }
}