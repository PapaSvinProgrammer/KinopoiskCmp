package com.mordva.kinopoiskkmp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import com.mordva.kinopoiskkmp.di.initKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApplication)
        }
    }
}