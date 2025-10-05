package com.mordva.security.internal.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.mordva.security.external.KeyStoreRepository
import com.mordva.security.internal.KeyManager
import com.mordva.security.internal.KeyStoreCustomManager
import com.mordva.security.internal.KeyStoreRepositoryImpl
import com.mordva.security.internal.SecurityStorage
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore(name = "kinopoisk_dev_settings")

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
actual val securityModulePlatform = module {
    singleOf(::KeyManager).bind<KeyStoreCustomManager>()

    single {
        get<Context>().dataStore
    }

    single {
        SecurityStorage(
            settings = DataStoreSettings(get())
        )
    }

    single<KeyStoreRepository> {
        KeyStoreRepositoryImpl(
            manager = get(),
            storage = get()
        )
    }
}