package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import com.mordva.ui.util.PrettyData
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SeasonDescription(
    modifier: Modifier = Modifier,
    countSeasons: Int,
    countSeries: Int
) {
    val prettySeasons = PrettyData.getPrettyCountSeasons(countSeasons)
    val prettySeries = PrettyData.getPrettyCountSeries(countSeries)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(Resources.Strings.SeasonsSeries),
                fontSize = Typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$prettySeasons, $prettySeries",
                fontSize = Typography.bodyMedium.fontSize
            )
        }

        Icon(
            painter = painterResource(Resources.Icons.KeyboardArrowRight),
            contentDescription = null
        )
    }
}