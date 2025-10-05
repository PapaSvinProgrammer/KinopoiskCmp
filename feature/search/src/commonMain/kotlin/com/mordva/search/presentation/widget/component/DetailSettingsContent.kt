package com.mordva.search.presentation.widget.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mordva.search.presentation.widget.row.CategoryRow
import com.mordva.ui.theme.Resources
import com.mordva.ui.util.ConvertData
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DetailSettingsContent(
    genresResult: List<String>,
    countriesResult: List<String>,
    yearResult: Pair<Int, Int>,
    onGenreClick: () -> Unit,
    onCountryClick: () -> Unit,
    onYearClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 30.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
    ) {
        CategoryRow(
            index = 0,
            s = stringResource(Resources.Strings.Genres),
            list = genresResult,
            defaultDescription = stringResource(Resources.Strings.Any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 1,
            s = stringResource(Resources.Strings.Countries),
            list = countriesResult,
            defaultDescription = stringResource(Resources.Strings.Any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 2,
            s = stringResource(Resources.Strings.Year),
            defaultDescription = ConvertData.convertYearRange(yearResult.first, yearResult.second),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )
    }
}