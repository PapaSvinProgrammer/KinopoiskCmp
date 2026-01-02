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
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Spacer
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
            .padding(horizontal = Spacer.M16, vertical = Spacer.M30)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(Spacer.M10)
            )
            .clip(RoundedCornerShape(Spacer.M10))
    ) {
        CategoryRow(
            index = 0,
            s = stringResource(Resources.Strings.Genres),
            list = checkedGenres.map { it.name },
            defaultDescription = stringResource(Resources.Strings.Any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 1,
            s = stringResource(Resources.Strings.Countries),
            list = checkedCountries.map { it.name },
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