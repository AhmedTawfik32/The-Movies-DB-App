package com.ahmedtawfik.moviesapp

//import org.koin.core.context.startKoin
import android.app.Application
import com.ahmedtawfik.moviesapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MoviesApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    apiServiceModule,
                    retrofitModule,
                    databaseModule
                )
            )
        }
    }
}