package com.mordva.security.internal.di

import com.mordva.security.external.KeyStoreRepository
import com.mordva.security.internal.KeyManager
import com.mordva.security.internal.KeyStoreCustomManager
import com.mordva.security.internal.KeyStoreRepositoryImpl
import com.mordva.security.internal.SecurityStorage
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
actual val securityModulePlatform = module {
    singleOf(::KeyManager).bind<KeyStoreCustomManager>()

    single {
        SecurityStorage(
            settings = NSUserDefaultsSettings(
                delegate = NSUserDefaults.standardUserDefaults
            ).toFlowSettings()
        )
    }

    single<KeyStoreRepository> {
        KeyStoreRepositoryImpl(
            manager = get(),
            storage = get()
        )
    }
}
