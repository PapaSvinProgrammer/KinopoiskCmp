package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.mordva.ui.theme.DsCornerShape
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.util.PosterType
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.fadingEdge
import com.mordva.util.windowWidthPercent
import kotlin.math.absoluteValue

@Composable
internal fun RandomMovieBackgroundPager(
    state: PagerState,
    itemState: RandomMoviePagerItemState,
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        targetState = itemState,
        transitionSpec = {
            fadeIn(
                tween(
                    durationMillis = 400,
                    delayMillis = 150,
                    easing = LinearOutSlowInEasing
                )
            ) togetherWith fadeOut(
                tween(
                    delayMillis = 250,
                    easing = FastOutLinearInEasing
                )
            )
        }
    ) { targetState ->
        when (targetState) {
            RandomMoviePagerItemState.PAGER_ITEM -> PagerItemHorizontalPager(
                state = state,
                items = items,
                modifier = modifier
            )

            RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> BottomSheetItemHorizontalPager(
                state = state,
                items = items,
                isVisible = itemState.isVisible(),
                modifier = modifier
            )
        }
    }
}

@Composable
private fun PagerItemHorizontalPager(
    state: PagerState,
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        state = state,
        userScrollEnabled = false,
        beyondViewportPageCount = 2,
        modifier = modifier.fillMaxWidth(),
    ) { page ->
        AsyncImage(
            model = items[page],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fadingEdge(FadingDefaults.bottomFade)
                .aspectRatio(PosterType.STANDARD.ratio)
        )
    }
}

@Composable
private fun BottomSheetItemHorizontalPager(
    state: PagerState,
    items: List<String>,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val itemWidth = windowWidthPercent(0.55f)
    val sidePeekWidth = windowWidthPercent(0.2f)

    val horizontalPadding = (windowWidthPercent() - itemWidth) / 2f + sidePeekWidth / 2f

    HorizontalPager(
        state = state,
        beyondViewportPageCount = 2,
        userScrollEnabled = false,
        pageSize = PageSize.Fixed(itemWidth),
        pageSpacing = -sidePeekWidth * 0.9f,
        snapPosition = SnapPosition.Center,
        contentPadding = PaddingValues(horizontal = horizontalPadding.coerceAtLeast(0.dp)),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = DsSpacer.M30)
    ) { page ->
        val pageOffset = state.getOffsetDistanceInPages(page)
        val absOffset = pageOffset.absoluteValue.coerceIn(0f, 1f)

        val visibleState = remember { MutableTransitionState(false) }

        LaunchedEffect(isVisible) {
            visibleState.targetState = isVisible
        }

        val scale = lerp(
            start = 0.65f,
            stop = 1f,
            fraction = 1f - absOffset
        )

        val alpha = lerp(
            start = 0.65f,
            stop = 1f,
            fraction = 1f - absOffset
        )

        AnimatedVisibility(
            visibleState = visibleState,
            enter = slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                ),
                initialOffsetY = { it }
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            modifier = Modifier
                .zIndex(1f - absOffset)
                .clip(DsCornerShape.M12)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
        ) {
            AsyncImage(
                model = items.getOrNull(page),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(PosterType.STANDARD.ratio)
                    .width(itemWidth)
            )
        }
    }
}

private fun RandomMoviePagerItemState.isVisible() = when (this) {
    RandomMoviePagerItemState.PAGER_ITEM -> false
    RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> true
}