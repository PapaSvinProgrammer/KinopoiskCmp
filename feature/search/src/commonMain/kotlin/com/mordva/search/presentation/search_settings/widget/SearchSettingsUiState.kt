package com.mordva.search.presentation.search_settings.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.category.ItemName
import com.mordva.ui.util.FormatDate
import com.mordva.util.Constants

@Immutable
internal data class SearchSettingsUiState(
    val selectedCategoryIndex: Int = 0,
    val selectedSortTypeIndex: Int = 0,
    val checkedGenres: List<ItemName> = listOf(),
    val checkedCountries: List<ItemName> = listOf(),
    val yearFilter: Pair<Int, Int> = Constants.LAST_YEAR to FormatDate.getCurrentYear(),
    val ratingSliderPosition: ClosedFloatingPointRange<Float> = 6f..10f,
    val yearPickerVisible: Boolean = false,
)