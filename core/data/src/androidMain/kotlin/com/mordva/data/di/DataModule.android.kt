package com.mordva.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.mordva.data.PreferencesRepositoryImpl
import com.mordva.domain.repository.PreferencesRepository
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore(name = "kinopoisk_dev_settings")

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
actual val dataModulePlatform = module {
    single {
        get<Context>().dataStore
    }

    single<PreferencesRepository> {
        PreferencesRepositoryImpl(
            settings = DataStoreSettings(get())
        )
    }
}