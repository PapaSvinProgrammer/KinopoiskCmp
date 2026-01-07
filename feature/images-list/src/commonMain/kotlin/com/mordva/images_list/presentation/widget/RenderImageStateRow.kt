package com.mordva.ui.widget.renderState

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.domain.model.image.Poster
import com.mordva.images_list.presentation.widget.ImageListUIState
import com.mordva.ui.theme.Icons
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import com.mordva.ui.widget.shimmer.ShimmerMovieRow
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RenderImagesStateRow(
    state: ImageListUIState,
    title: String,
    onClick: (Poster) -> Unit,
    onShowAll: () -> Unit
) {

}

@Composable
private fun MainImageContent(
    title: String,
    list: List<Poster>,
    onClick: (Poster) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = list,
        key = { it },
        lastItemCard = {
            LastItemCard(
                height = 130.dp,
                width = 90.dp,
                onClick = onShowAll
            )
        }
    ) {
        AsyncImage(
            model = it.url,
            contentDescription = null,
            error = painterResource(Icons.Image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(130.dp)
                .wrapContentWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick(it) }
        )
    }
}