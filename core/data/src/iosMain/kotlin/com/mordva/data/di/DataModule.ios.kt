package com.mordva.data.di

import com.mordva.data.PreferencesRepositoryImpl
import com.mordva.domain.repository.PreferencesRepository
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
actual val dataModulePlatform = module {
    single<PreferencesRepository> {
        PreferencesRepositoryImpl(
            NSUserDefaultsSettings(
                delegate = NSUserDefaults.standardUserDefaults
            ).toFlowSettings()
        )
    }
}