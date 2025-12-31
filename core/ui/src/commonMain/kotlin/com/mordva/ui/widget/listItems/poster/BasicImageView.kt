package com.mordva.ui.widget.listItems.poster

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.Resources
import com.mordva.ui.util.PosterType
import org.jetbrains.compose.resources.painterResource

@Composable
fun BasicImageView(
    model: String?,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = model,
        contentDescription = null,
        error = painterResource(Resources.Icons.Image),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun StandardImageLarge(
    model: String?,
    modifier: Modifier = Modifier,
) {
    BasicImageView(
        model = model,
        modifier = modifier
            .width(140.dp)
            .wrapContentHeight()
            .aspectRatio(PosterType.STANDARD.ratio)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun StandardImageMedium(
    model: String?,
    modifier: Modifier = Modifier,
) {
    BasicImageView(
        model = model,
        modifier = modifier
            .width(90.dp)
            .wrapContentHeight()
            .aspectRatio(PosterType.STANDARD.ratio)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun StandardImageSmall(
    model: String?,
    modifier: Modifier = Modifier,
) {
    BasicImageView(
        model = model,
        modifier = modifier
            .width(60.dp)
            .wrapContentHeight()
            .aspectRatio(PosterType.STANDARD.ratio)
            .clip(RoundedCornerShape(10.dp))
    )
}