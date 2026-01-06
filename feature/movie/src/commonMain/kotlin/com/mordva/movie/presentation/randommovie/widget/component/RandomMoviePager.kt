package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.mordva.model.movie.Movie
import com.mordva.util.windowHeightPercent
import com.mordva.util.windowWidthPercent
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RandomMoviePager(
    state: PagerState,
    items: List<Movie>,
    modifier: Modifier = Modifier,
) {
    val itemWidth = windowWidthPercent(0.7f)
    val itemHeight = windowHeightPercent(0.75f)
    val fullSidePadding = (windowWidthPercent() - itemWidth) / 2f

    HorizontalPager(
        state = state,
        beyondViewportPageCount = 2,
        pageSize = PageSize.Fixed(itemWidth),
        snapPosition = SnapPosition.Center,
        contentPadding = PaddingValues(horizontal = fullSidePadding),
        modifier = modifier.fillMaxWidth()
    ) { page ->
        val movieItem = items[page]
        val pageOffset = state.getOffsetDistanceInPages(page).absoluteValue.coerceIn(0f, 1f)

        val scale = lerp(
            start = 0.85f,
            stop = 1f,
            fraction = 1f - pageOffset
        )

        val alpha = lerp(
            start = 0.7f,
            stop = 1f,
            fraction = 1f - pageOffset
        )

        RandomMoviePagerItem(
            title = movieItem.name.toString(),
            imageUrl = movieItem.poster?.url.toString(),
            rating = movieItem.rating?.kp ?: 0f,
            genres = movieItem.genres.map { it.name },
            modifier = Modifier
                .width(itemWidth)
                .height(itemHeight)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
        )
    }
}