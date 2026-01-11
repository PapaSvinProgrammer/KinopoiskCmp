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
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.PrettyData
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SeasonDescription(
    modifier: Modifier = Modifier,
    countSeasons: Int,
    countSeries: Int,
) {
    val prettySeasons = PrettyData.getPrettyCountSeasons(countSeasons)
    val prettySeries = PrettyData.getPrettyCountSeries(countSeries)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = DsSpacer.M16,
                vertical = DsSpacer.M12
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(Strings.SeasonsSeries),
                fontSize = DsTextSize.M14,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "$prettySeasons, $prettySeries",
                fontSize = DsTextSize.M14,
                fontWeight = FontWeight.Light
            )
        }

        Icon(
            painter = painterResource(Icons.KeyboardArrowRight),
            contentDescription = null
        )
    }
}