package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.PosterType
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun OverlapImages(
    images: List<String>,
    modifier: Modifier = Modifier,
    overlapFraction: Float = 0.3f,
    maxCount: Int = 10,
    imageSize: Dp = 30.dp
) {
    if (images.isEmpty()) return

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val overlapDp = imageSize * overlapFraction
        val count = (maxWidth / imageSize - 1)
            .toInt()
            .coerceAtLeast(1)
            .coerceAtMost(maxCount)

        val isCompressed = count < images.size

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogosContent(
                images = images.take(count),
                imageSize = imageSize,
                overlapDp = overlapDp,
                isCompressed = isCompressed,
                remains = images.size - count,
            )
        }
    }
}

@Composable
private fun LogosContent(
    images: List<String>,
    imageSize: Dp,
    overlapDp: Dp,
    isCompressed: Boolean,
    remains: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(-overlapDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        images.forEachIndexed { index, poster ->
            AsyncImage(
                model = poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageSize)
                    .aspectRatio(PosterType.SQUARE.ratio)
                    .clip(CircleShape)
                    .zIndex(index.toFloat())
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    )
            )
        }

        if (isCompressed) {
            LastItem(
                count = remains,
                modifier = Modifier
                    .size(imageSize)
                    .zIndex(Float.MAX_VALUE)
            )
        }
    }
}

@Composable
private fun LastItem(
    count: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
    ) {
        Text(
            text = stringResource(Strings.OverlapLogsCount, count),
            fontWeight = FontWeight.Bold,
            fontSize = DsTextSize.M14,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}