package com.mordva.search.presentation.search_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.search.presentation.search_screen.widget.dialog.YearPickerDialog
import com.mordva.search.presentation.search_settings.widget.SearchSettingsScreenEvent
import com.mordva.search.presentation.search_settings.widget.SearchSettingsScreenEvent.ShowSearchSettingsList
import com.mordva.search.presentation.search_settings_list.SearchSettingsListType.COUNTRY
import com.mordva.search.presentation.search_settings_list.SearchSettingsListType.GENRE
import com.mordva.search.presentation.widget.component.DetailSettingsContent
import com.mordva.search.presentation.widget.row.RatingRow
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Resources.StringArray.optionsCategory
import com.mordva.ui.theme.Resources.StringArray.optionsSortType
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.widget.other.TitleTopBarText
import com.mordva.util.ObserveAsEvents
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchSettingsScreen(
    viewModel: SearchSettingsViewModel,
    eventHandler: (SearchSettingsScreenEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.uiEvents) {
        when (it) {
            SearchSettingsScreenEvent.GoBack -> eventHandler(SearchSettingsScreenEvent.GoBack)
            is ShowSearchSettingsList -> eventHandler(ShowSearchSettingsList(it.type))
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(Resources.Strings.SearchSettings))
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackClicked() }) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.resetAllSettings() }) {
                        Text(
                            text = stringResource(Resources.Strings.Reset),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(DsSpacer.M16))

            SegmentedButton(
                selectedIndex = uiState.selectedCategoryIndex,
                list = optionsCategory,
                onClick = { viewModel.updateSelectedCategoryIndex(it) }
            )

            DetailSettingsContent(
                checkedGenres = uiState.checkedGenres,
                checkedCountries = uiState.checkedCountries,
                yearResult = uiState.yearFilter,
                onCountryClick = { viewModel.updateCurrentListType(COUNTRY) },
                onGenreClick = { viewModel.updateCurrentListType(GENRE) },
                onYearClick = { viewModel.updateVisibleYearPicker(true) }
            )

            RatingRow(
                sliderPosition = uiState.ratingSliderPosition,
                onChange = { viewModel.updateRatingSliderPosition(it) }
            )

            Spacer(modifier = Modifier.height(DsSpacer.M16))
            Text(
                text = stringResource(Resources.Strings.SortingBy),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(DsSpacer.M16)
            )

            SegmentedButton(
                selectedIndex = uiState.selectedSortTypeIndex,
                list = optionsSortType,
                onClick = { viewModel.updateSelectedSortTypeIndex(it) }
            )
        }
    }

    if (uiState.yearPickerVisible) {
        YearPickerDialog(
            onDismiss = { viewModel.updateVisibleYearPicker(false) },
            onConfirm = { start, end ->
                viewModel.updateYearFilter(start, end)
                viewModel.updateVisibleYearPicker(false)
            }
        )
    }
}

@Composable
private fun ColumnScope.SegmentedButton(
    selectedIndex: Int,
    list: List<StringResource>,
    onClick: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DsSpacer.M16)
            .align(Alignment.CenterHorizontally)
    ) {
        list.forEachIndexed { index, item ->
            SegmentedButton(
                selected = index == selectedIndex,
                label = {
                    Text(
                        text = stringResource(item),
                        maxLines = 1
                    )
                },
                icon = { },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = list.size,
                    baseShape = RoundedCornerShape(DsSpacer.M10)
                ),
                onClick = { onClick(index) }
            )
        }
    }
}
