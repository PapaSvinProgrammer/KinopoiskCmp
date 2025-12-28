package com.mordva.search.presentation.search_settings

import com.mordva.search.domain.SearchSettingsItemListManager
import com.mordva.search.presentation.search_settings.widget.SearchSettingsUiState
import com.mordva.ui.util.FormatDate
import com.mordva.util.BaseViewModel
import com.mordva.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class SearchSettingsViewModel(
    private val itemListManager: SearchSettingsItemListManager,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(SearchSettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getGenres()
        getCountries()
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

    private fun getGenres() = launchWithoutOld(GET_GENRES_JOB) {
        itemListManager.getGenres()
    }

    private fun getCountries() = launchWithoutOld(GET_COUNTRIES_JOB) {
        itemListManager.getCountries()
    }

    private companion object {
        const val GET_GENRES_JOB = "get_genres"
        const val GET_COUNTRIES_JOB = "get_countries"
    }
}