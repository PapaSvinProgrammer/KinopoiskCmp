package com.mordva.search.presentation.searchSettings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.search.presentation.navigation.SearchResultRoute
import com.mordva.search.presentation.searchScreen.widget.dialog.YearPickerDialog
import com.mordva.search.presentation.widget.component.DetailSettingsContent
import com.mordva.search.presentation.widget.component.SearchListLayout
import com.mordva.search.presentation.widget.row.RatingRow
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.util.ConvertData
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchSettingsScreen(
    navController: NavController,
    viewModel: SearchSettingsViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getGenres()
        viewModel.getCounties()
    }

    val optionsCategory = listOf(
        stringResource(Resources.Strings.All),
        stringResource(Resources.Strings.Movies),
        stringResource(Resources.Strings.Serials)
    )

    val optionsSortType = listOf(
        stringResource(Resources.Strings.ByRatingShort),
        stringResource(Resources.Strings.ByPopularShort),
        stringResource(Resources.Strings.ByDateShort)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(Resources.Strings.SearchSettings))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                SegmentedButton(
                    selectedIndex = uiState.selectedCategoryIndex,
                    list = optionsCategory,
                    onClick = { viewModel.updateSelectedCategoryIndex(it) }
                )

                DetailSettingsContent(
                    genresResult = uiState.resultGenres.filter { it.isChecked }.map { it.title },
                    countriesResult = uiState.resultCountries.filter { it.isChecked }.map { it.title },
                    yearResult = uiState.yearFilter,
                    onCountryClick = { viewModel.updateCountryVisible(true) },
                    onGenreClick = { viewModel.updateGenreVisible(true) },
                    onYearClick = { viewModel.updateVisibleYearPicker(true) }
                )

                RatingRow(
                    sliderPosition = uiState.ratingSliderPosition,
                    onChange = { viewModel.updateRatingSliderPosition(it) }
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(Resources.Strings.SortingBy),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(15.dp)
                )

                SegmentedButton(
                    selectedIndex = uiState.selectedSortTypeIndex,
                    list = optionsSortType,
                    onClick = { viewModel.updateSelectedSortTypeIndex(it) }
                )
            }

            SuccessButton {
                val convertData = ConvertData.convertQueryParameters(
                    category = optionsCategory[uiState.selectedCategoryIndex],
                    sortBy = optionsSortType[uiState.selectedSortTypeIndex],
                    genres = uiState.resultGenres.filter { it.isChecked }.map { it.title },
                    counties = uiState.resultCountries.filter { it.isChecked }.map { it.title },
                    rating = uiState.ratingSliderPosition,
                    year = uiState.yearFilter
                )

                navController.navigate(SearchResultRoute(convertData)) {
                    launchSingleTop = true
                }
            }
        }
    }

    SearchListLayout(
        visible = uiState.genreListVisible,
        title = stringResource(Resources.Strings.Genres),
        onClose = { viewModel.updateGenreVisible(false)},
        list = uiState.resultGenres,
        onClick = { viewModel.updateResultGenres(it) },
        onReset = { viewModel.resetGenres() }
    )

    SearchListLayout(
        visible = uiState.countryListVisible,
        title = stringResource(Resources.Strings.Countries),
        onClose = { viewModel.updateCountryVisible(false) },
        list = uiState.resultCountries,
        onClick = { viewModel.updateResultCountries(it) },
        onReset = { viewModel.resetCountries() }
    )

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
    list: List<String>,
    onClick: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .align(Alignment.CenterHorizontally)
    ) {
        list.forEachIndexed { index, item ->
            SegmentedButton(
                selected = index == selectedIndex,
                label = {
                    Text(
                        text = item,
                        maxLines = 1
                    )
                },
                icon = {  },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = list.size,
                    baseShape = RoundedCornerShape(10.dp)
                ),
                onClick = { onClick(index) }
            )
        }
    }
}

@Composable
private fun BoxScope.SuccessButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(30.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(Resources.Strings.Show),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}