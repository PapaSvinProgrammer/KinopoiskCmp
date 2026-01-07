package com.mordva.sqlite.di

import com.mordva.sqlite.AppDatabase
import com.mordva.sqlite.getDatabase
import org.koin.dsl.module

actual val roomModulePlatform = module {
    single<AppDatabase> { getDatabase(get()) }
}