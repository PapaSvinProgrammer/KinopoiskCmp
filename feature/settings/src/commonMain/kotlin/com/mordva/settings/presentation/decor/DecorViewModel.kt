package com.mordva.settings.presentation.decor

import androidx.lifecycle.ViewModel
import com.mordva.domain.repository.PreferencesRepository
import com.mordva.settings.presentation.widget.state.DecorUiState
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class DecorViewModel(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DecorUiState())
    val uiState = _uiState.asStateFlow()

    fun setTheme(state: Int) = launchWithoutOld(SET_THEME_JOB) {
        preferencesRepository.setThemeState(state)
    }

    fun getTheme() = launchWithoutOld(GET_THEME_JOB) {
        preferencesRepository.getThemeState().collect {
            _uiState.value = _uiState.value.copy(themeState = it)
        }
    }

    private companion object {
        private const val SET_THEME_JOB = "set_theme"
        private const val GET_THEME_JOB = "get_theme"
    }
}
