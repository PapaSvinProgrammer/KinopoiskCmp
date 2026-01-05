package com.mordva.movie.presentation.randommovie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.ui.util.PosterType

@Composable
internal fun RandomMoviePagerItem(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(60.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(30.dp)
    ) {
        AsyncImage(
            model = "https://avatars.mds.yandex.net/i?id=6585f3329576ab9bfcced8caa30ed2cdea00854c-5232907-images-thumbs&n=13",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(PosterType.STANDARD.ratio)
                .wrapContentWidth()
                .clip(RoundedCornerShape(50.dp))
        )
    }
}