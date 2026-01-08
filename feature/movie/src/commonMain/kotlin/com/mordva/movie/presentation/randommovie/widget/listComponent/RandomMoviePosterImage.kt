package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.util.PosterType

internal fun LazyListScope.randomMoviePosterImage(
    imageUrl: String,
    width: Dp,
    scale: Float,
    onPositioned: (LayoutCoordinates) -> Unit,
) {
    item {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = DsSpacer.M20, start = DsSpacer.M20, end = DsSpacer.M20)
                .width(width)
                .aspectRatio(PosterType.STANDARD.ratio)
                .scale(scale)
                .clip(RoundedCornerShape(50.dp))
                .onGloballyPositioned(onPositioned)
        )
    }
}