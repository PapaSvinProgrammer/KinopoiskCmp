package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.mordva.domain.model.movie.Movie
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
    val fullSidePadding = (windowWidthPercent() - windowWidthPercent(0.7f)) / 2f
    var itemState by remember { mutableStateOf(RandomMoviePagerItemState.PAGER_ITEM) }

    val animatedItemWidth = animateItemWidth(itemState)

    HorizontalPager(
        state = state,
        beyondViewportPageCount = 2,
        pageSize = PageSize.Fixed(animatedItemWidth.value),
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
            movie = movieItem,
            state = itemState,
            modifier = Modifier
                .width(animatedItemWidth.value)
                .height(windowHeightPercent(0.75f))
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
                .clickable(
                    indication = null,
                    interactionSource = null,
                ) {
                    itemState = when (itemState) {
                        RandomMoviePagerItemState.PAGER_ITEM -> RandomMoviePagerItemState.BOTTOM_SHEET_ITEM
                        RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> RandomMoviePagerItemState.PAGER_ITEM
                    }
                }
        )
    }
}

@Composable
private fun animateItemWidth(itemState: RandomMoviePagerItemState) = animateDpAsState(
    targetValue = itemState.toItemWidth()
)

@Composable
private fun RandomMoviePagerItemState.toItemWidth() = when (this) {
    RandomMoviePagerItemState.PAGER_ITEM -> windowWidthPercent(0.7f)
    RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> windowWidthPercent(1f)
}