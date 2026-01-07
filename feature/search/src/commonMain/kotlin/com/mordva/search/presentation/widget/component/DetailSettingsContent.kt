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
import com.mordva.domain.model.category.ItemName
import com.mordva.search.presentation.widget.row.CategoryRow
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.ConvertData
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DetailSettingsContent(
    checkedGenres: List<ItemName>,
    checkedCountries: List<ItemName>,
    yearResult: Pair<Int, Int>,
    onGenreClick: () -> Unit,
    onCountryClick: () -> Unit,
    onYearClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = DsSpacer.M16, vertical = DsSpacer.M30)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(DsSpacer.M10)
            )
            .clip(RoundedCornerShape(DsSpacer.M10))
    ) {
        CategoryRow(
            index = 0,
            s = stringResource(Strings.Genres),
            list = checkedGenres.map { it.name },
            defaultDescription = stringResource(Strings.Any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 1,
            s = stringResource(Strings.Countries),
            list = checkedCountries.map { it.name },
            defaultDescription = stringResource(Strings.Any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 2,
            s = stringResource(Strings.Year),
            defaultDescription = ConvertData.convertYearRange(yearResult.first, yearResult.second),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )
    }
}