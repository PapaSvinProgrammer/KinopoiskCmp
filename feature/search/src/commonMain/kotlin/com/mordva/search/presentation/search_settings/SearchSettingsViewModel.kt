package com.mordva.search.presentation.search_settings

import androidx.lifecycle.viewModelScope
import com.mordva.search.domain.SearchSettingsItemListManager
import com.mordva.search.presentation.search_settings.widget.SearchSettingsScreenEvent
import com.mordva.search.presentation.search_settings.widget.SearchSettingsUiState
import com.mordva.search.presentation.search_settings_list.SearchSettingsListType
import com.mordva.ui.util.FormatDate
import com.mordva.util.BaseViewModel
import com.mordva.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchSettingsViewModel(
    private val itemListManager: SearchSettingsItemListManager,
) : BaseViewModel<SearchSettingsScreenEvent>() {
    private val _uiState = MutableStateFlow(SearchSettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getGenres()
        getCountries()

        itemListManager.state
            .map { it.checked }
            .distinctUntilChanged()
            .onEach { updateCheckedLists() }
            .launchIn(viewModelScope)
    }

    fun updateCurrentListType(type: SearchSettingsListType) {
        itemListManager.setCurrentType(type)
        sendEvent(SearchSettingsScreenEvent.ShowSearchSettingsList(type))
    }

    fun updateYearFilter(start: Int, end: Int) {
        _uiState.update {
            it.copy(yearFilter = start to end)
        }
    }

    fun updateVisibleYearPicker(state: Boolean) {
        _uiState.update {
            it.copy(yearPickerVisible = state)
        }
    }

    fun updateSelectedCategoryIndex(index: Int) {
        _uiState.update {
            it.copy(selectedCategoryIndex = index)
        }
    }

    fun updateSelectedSortTypeIndex(index: Int) {
        _uiState.update {
            it.copy(selectedSortTypeIndex = index)
        }
    }

    fun updateRatingSliderPosition(position: ClosedFloatingPointRange<Float>) {
        _uiState.update {
            it.copy(ratingSliderPosition = position)
        }
    }

    fun resetAllSettings() {
        _uiState.update {
            it.copy(
                selectedCategoryIndex = 0,
                selectedSortTypeIndex = 0,
                ratingSliderPosition = 6f..10f,
                yearFilter = Constants.LAST_YEAR to FormatDate.getCurrentYear(),
            )
        }

        itemListManager.reset()
    }

    fun onBackClicked() {
        sendEvent(SearchSettingsScreenEvent.GoBack)
    }

    private fun updateCheckedLists() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(checkedGenres = itemListManager.getCheckedGenres())
            }
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(checkedCountries = itemListManager.getCheckedCountries())
            }
        }
    }

    private fun getGenres() = launchWithoutOld(GET_GENRES_JOB) {
        itemListManager.initGenres()
    }

    private fun getCountries() = launchWithoutOld(GET_COUNTRIES_JOB) {
        itemListManager.initCountries()
    }

    private companion object {
        const val GET_GENRES_JOB = "get_genres"
        const val GET_COUNTRIES_JOB = "get_countries"
    }
}