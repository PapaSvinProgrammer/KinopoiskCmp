package com.mordva.data

import com.mordva.domain.repository.PreferencesRepository
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSettingsApi::class)
internal class PreferencesRepositoryImpl(
    private val settings: FlowSettings
) : PreferencesRepository {
    override suspend fun setThemeState(state: Int) {
        settings.putInt(THEME_STATE, state)
    }

    override suspend fun setEntryState(state: Boolean) {
        settings.putBoolean(ENTRY_STATE, state)
    }

    override suspend fun setAuthorizationState(state: Boolean) {
        settings.putBoolean(AUTH_STATE, state)
    }

    override fun getThemeState(): Flow<Int> {
        return settings.getIntFlow(THEME_STATE, 1)
    }

    override fun getEntryState(): Flow<Boolean> {
        return settings.getBooleanFlow(ENTRY_STATE, false)
    }

    override fun getAuthorizationState(): Flow<Boolean> {
        return settings.getBooleanFlow(AUTH_STATE, false)
    }

    private companion object {
        const val THEME_STATE = "theme_state"
        const val ENTRY_STATE = "entry_state"
        const val AUTH_STATE = "auth_state"
    }
}
