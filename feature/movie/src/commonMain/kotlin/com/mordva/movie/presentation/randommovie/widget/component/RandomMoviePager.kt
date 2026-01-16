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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.mordva.movie.presentation.randommovie.widget.RandomMovieItemState
import com.mordva.util.windowHeightPercent
import com.mordva.util.windowWidthPercent
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RandomMoviePager(
    state: PagerState,
    items: List<RandomMovieItemState>,
    itemState: RandomMoviePagerItemType,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
) {
    val fullSidePadding = (windowWidthPercent() - windowWidthPercent(0.7f)) / 2f
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
            itemState = movieItem,
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
                    onClick = onItemClick,
                )
        )
    }
}

@Composable
private fun animateItemWidth(itemState: RandomMoviePagerItemType) = animateDpAsState(
    targetValue = itemState.toItemWidth()
)

@Composable
private fun RandomMoviePagerItemType.toItemWidth() = when (this) {
    RandomMoviePagerItemType.PAGER_ITEM -> windowWidthPercent(0.7f)
    RandomMoviePagerItemType.BOTTOM_SHEET_ITEM -> windowWidthPercent(1f)
}
