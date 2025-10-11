package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.model.image.Poster
import com.mordva.ui.theme.Resources
import com.mordva.ui.util.toAspectRatio
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.imagesItem(
    images: List<Poster>,
    onShowAll: () -> Unit,
) {
    if (images.isEmpty()) return

    item {
        TitleRow(title = stringResource(Resources.Strings.Images)) {
            onShowAll()
        }

        DefaultLazyRow(
            list = images,
            lastItemCard = {
                LastItemCard(
                    width = 200.dp,
                    height = 160.dp
                )
            }
        ) { poster ->
            val aspectRatio = toAspectRatio(poster.height, poster.width)

            AsyncImage(
                model = poster.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(Resources.Icons.Image),
                modifier = Modifier
                    .height(160.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .aspectRatio(aspectRatio)
            )
        }
    }
}