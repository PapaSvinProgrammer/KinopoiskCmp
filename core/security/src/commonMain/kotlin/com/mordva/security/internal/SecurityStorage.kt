package com.mordva.security.internal

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings

@OptIn(ExperimentalSettingsApi::class)
internal class SecurityStorage(
    private val settings: FlowSettings
) {
    suspend fun save(name: String, value: String) {
        settings.putString(name, value)
    }

    @OptIn(ExperimentalSettingsApi::class)
    suspend fun get(name: String): String {
        return settings.getString(name, "")
    }
}
