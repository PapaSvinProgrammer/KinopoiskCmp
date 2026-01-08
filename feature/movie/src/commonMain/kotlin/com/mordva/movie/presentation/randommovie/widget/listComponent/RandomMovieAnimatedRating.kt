package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Gold
import com.mordva.ui.theme.Icons
import com.mordva.ui.util.customOffset
import org.jetbrains.compose.resources.painterResource

internal fun LazyListScope.randomMovieAnimatedRating(
    rating: Float,
    starOffsets: List<Animatable<Float, AnimationVector1D>>,
) {
    item {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DsSpacer.M4),
        ) {
            Text(
                text = rating.toString(),
                fontSize = DsTextSize.M12,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.customOffset(yOffset = starOffsets.first().value),
            )

            repeat(5) { index ->
                val fillFraction = rating.fillFractionForStar(index + 1)

                Icon(
                    painter = painterResource(if (fillFraction > 0f) Icons.StarFill else Icons.Star),
                    contentDescription = null,
                    tint = if (fillFraction > 0f)
                        Gold
                    else
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    modifier = Modifier
                        .size(DsSpacer.M16)
                        .customOffset(yOffset = starOffsets[index].value)
                )
            }
        }
    }
}

private fun Float.fillFractionForStar(starIndex: Int): Float {
    val fullStarsThreshold = starIndex * 2
    val previousThreshold = (starIndex - 1) * 2

    return when {
        this >= fullStarsThreshold -> 1f
        this > previousThreshold -> (this - previousThreshold) / 2f
        else -> 0f
    }
}