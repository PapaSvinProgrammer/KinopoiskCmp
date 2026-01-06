package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.util.PosterType
import com.mordva.ui.widget.component.DsChip
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.fadingEdge

@Composable
internal fun RandomMoviePagerItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    rating: Float,
    genres: List<String>,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DsSpacer.M16),
        modifier = modifier
            .fadingEdge(FadingDefaults.bottomFade)
            .clip(RoundedCornerShape(60.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = DsSpacer.M20, start = DsSpacer.M20, end = DsSpacer.M20)
                .aspectRatio(PosterType.STANDARD.ratio)
                .wrapContentWidth()
                .clip(RoundedCornerShape(50.dp))
        )

        Text(
            text = title,
            fontSize = DsTextSize.M22,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = DsSpacer.M10)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(DsSpacer.M10),
            modifier = Modifier
                .padding(horizontal = DsSpacer.M10)
                .horizontalScroll(rememberScrollState())
        ) {
            genres.forEach {
                DsChip(it)
            }
        }

        RandomMovieRatingKp(rating)
    }
}
