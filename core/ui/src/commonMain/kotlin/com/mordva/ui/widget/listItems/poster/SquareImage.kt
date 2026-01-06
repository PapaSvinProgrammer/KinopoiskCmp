package com.mordva.ui.widget.listItems.poster

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.mordva.ui.theme.Icons
import com.mordva.ui.util.PosterType
import org.jetbrains.compose.resources.painterResource

@Composable
fun SquareImage(
    model: String?,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = model,
        contentDescription = null,
        error = painterResource(Icons.Image),
        contentScale = ContentScale.FillBounds,
        modifier = modifier.rotate(PosterType.SQUARE.ratio)
    )
}