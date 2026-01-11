package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.domain.model.image.Poster
import com.mordva.movie.presentation.movie.widget.component.LastItemCardImage
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.ui.theme.DsCornerShape
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.toAspectRatio
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RandomMovieImages(
    images: List<Poster>,
    onAction: (RandomMovieAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (images.isEmpty()) return

    Column {
        TitleRow(
            title = stringResource(Strings.Images),
            onClick = { onAction(RandomMovieAction.ShowAllImagesClicked) },
            modifier = modifier,
        )

        DefaultLazyRow(
            list = images,
            lastItemCard = {
                LastItemCardImage { onAction(RandomMovieAction.ShowAllImagesClicked) }
            },
            modifier = modifier,
        ) { poster ->
            val aspectRatio = toAspectRatio(poster.height, poster.width)

            AsyncImage(
                model = poster.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(Icons.Image),
                modifier = Modifier
                    .height(160.dp)
                    .clip(DsCornerShape.M10)
                    .aspectRatio(aspectRatio)
                    .clickable(
                        indication = null,
                        interactionSource = null,
                        onClick = { onAction(RandomMovieAction.ImageClicked(poster)) }
                    )
            )
        }
    }
}