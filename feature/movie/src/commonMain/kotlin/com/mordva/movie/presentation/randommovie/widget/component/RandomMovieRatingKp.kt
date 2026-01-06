package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Gold
import com.mordva.ui.theme.Icons
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RandomMovieRatingKp(value: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsSpacer.M4)
    ) {
        Text(
            text = value.toString(),
            fontSize = DsTextSize.M12,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(horizontalArrangement = Arrangement.spacedBy(DsSpacer.M4)) {
            for (i in 1..5) {
                val fillFraction = calculateFillFraction(value, i)

                Icon(
                    painter = if (fillFraction > 0f) {
                        painterResource(Icons.StarFill)
                    } else {
                        painterResource(Icons.Star)
                    },
                    contentDescription = null,
                    tint = if (fillFraction > 0f) {
                        Gold
                    } else {
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    },
                    modifier = Modifier.size(DsSpacer.M16)
                )
            }
        }
    }
}

private fun calculateFillFraction(value: Float, i: Int) = when {
    value >= i * 2 -> 1f
    value > (i - 1) * 2 -> (value - (i - 1) * 2) / 2f
    else -> 0f
}