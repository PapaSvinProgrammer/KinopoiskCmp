package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import coil3.compose.AsyncImage
import com.mordva.domain.model.image.Poster
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.PosterType
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun WatchabilityDescription(
    modifier: Modifier = Modifier,
    count: Int,
    images: List<Poster?>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = DsSpacer.M12, horizontal = DsSpacer.M16),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(Strings.WhereCanWatch),
                fontWeight = FontWeight.Medium,
                fontSize = DsTextSize.M14
            )

            Text(
                text = "Доступно в $count кинотеатрах",
                fontWeight = FontWeight.Light,
                fontSize = DsTextSize.M14,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(DsSpacer.M10)
        ) {
            images.forEach {
                AsyncImage(
                    model = it?.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(DsSpacer.M30)
                        .aspectRatio(PosterType.SQUARE.ratio)
                        .clip(CircleShape)
                )
            }
        }
    }
}