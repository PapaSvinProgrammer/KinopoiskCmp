package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mordva.movie.presentation.randommovie.widget.RandomMovieItemState
import com.mordva.movie.presentation.randommovie.widget.getGenres
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieAnimatedRating
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieDescription
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieDirectorTitle
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieGenresRow
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieImages
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieList
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMoviePersonList
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMoviePosterImage
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieSeasonDescription
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieTitle
import com.mordva.movie.presentation.randommovie.widget.listComponent.RandomMovieWatchability
import com.mordva.ui.theme.DsCornerShape
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.customOffset
import com.mordva.ui.util.measureWidthOnce
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.component.ErrorScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Не используется LazyColumn как root контейнер, так как его спцифика начинает ломать UI.
// Если поднять элементы и проскролить вверх, то может возникнуть ситуация, что элемениты исчезнут,
// так как LazyColumn почистит их

@Composable
internal fun RandomMoviePagerItem(
    modifier: Modifier = Modifier,
    itemState: RandomMovieItemState,
    state: RandomMoviePagerItemType,
) {
    val imageScale = remember { Animatable(1f) }
    val topOffsetY = remember { Animatable(0f) }
    val bottomOffsetY = remember { Animatable(0f) }
    val starOffsetsY = remember { List(5) { Animatable(0f) } }
    val personOffsetsY = remember { List(5) { Animatable(0f) } }

    var imageHeightPx by remember { mutableStateOf(0f) }
    var imageWidth by remember { mutableStateOf(0.dp) }

    LaunchedEffect(state) {
        if (state == RandomMoviePagerItemType.BOTTOM_SHEET_ITEM) {
            imageScale.animateImageScale(state)
        } else {
            launch { imageScale.animateImageScale(state) }
        }

        launch {
            topOffsetY.animateOffsetSpring(state, imageHeightPx)
        }

        starOffsetsY.forEachIndexed { index, animatable ->
            launch {
                delay(index * 60L)
                animatable.animateOffsetSpring(state, imageHeightPx)
            }
        }

        if (state == RandomMoviePagerItemType.BOTTOM_SHEET_ITEM) {
            delay(300)
        }

        personOffsetsY.forEachIndexed { index, animatable ->
            launch {
                delay(index * 60L)
                animatable.animateOffsetSpring(state, imageHeightPx)
            }
        }

        launch {
            bottomOffsetY.animateOffsetSpring(state, imageHeightPx)
        }
    }

    when (itemState) {
        RandomMovieItemState.Error -> ErrorScreen()
        RandomMovieItemState.Loading -> BasicLoadingBox()
        is RandomMovieItemState.Success -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(DsSpacer.M16),
                modifier = modifier
                    .clip(DsCornerShape.M60)
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
                    .measureWidthOnce { imageWidth = it }
                    .verticalScroll(rememberScrollState())
            ) {
                RandomMoviePosterImage(
                    imageUrl = itemState.movie.poster?.url.toString(),
                    width = imageWidth,
                    scale = imageScale.value,
                    onPositioned = { imageHeightPx = it.size.height.toFloat() }
                )

                RandomMovieTitle(
                    title = itemState.movie.name.toString(),
                    offsetY = topOffsetY.value
                )

                RandomMovieGenresRow(
                    genres = itemState.getGenres(),
                    offsetY = topOffsetY.value
                )

                RandomMovieDirectorTitle(
                    text = itemState.director,
                    modifier = Modifier.customOffset(yOffset = topOffsetY.value)
                )

                RandomMovieAnimatedRating(
                    rating = itemState.movie.rating?.kp ?: 0f,
                    starOffsets = starOffsetsY
                )

                RandomMovieDescription(
                    description = itemState.movie.description.toString(),
                    onClick = {},
                    modifier = Modifier.customOffset(yOffset = topOffsetY.value)
                )

                RandomMoviePersonList(
                    title = Strings.Persons,
                    list = itemState.movie.persons,
                    yOffset = personOffsetsY,
                    onAction = { },
                )

                Column {
                    RandomMovieSeasonDescription(
                        movie = itemState.movie,
                        modifier = Modifier.customOffset(yOffset = bottomOffsetY.value)
                    )

                    RandomMovieWatchability(
                        items = itemState.movie.watchability.items,
                        onWatchabilityClick = {},
                        modifier = Modifier.customOffset(yOffset = bottomOffsetY.value)
                    )
                }

                RandomMovieImages(
                    images = itemState.images,
                    onAction = {},
                    modifier = Modifier.customOffset(yOffset = bottomOffsetY.value)
                )

                RandomMovieList(
                    titleRes = Strings.SequalsAndPrequals,
                    list = itemState.movie.sequelsAndPrequels,
                    onClick = {},
                    modifier = Modifier.customOffset(yOffset = bottomOffsetY.value)
                )

                RandomMovieList(
                    titleRes = Strings.SimilarMovies,
                    list = itemState.movie.similarMovies,
                    onClick = {},
                    modifier = Modifier.customOffset(yOffset = bottomOffsetY.value)
                )
            }
        }
    }
}

private suspend fun Animatable<Float, AnimationVector1D>.animateOffsetSpring(
    state: RandomMoviePagerItemType,
    yOffset: Float,
) = animateTo(
    targetValue = state.toImageOffsetValue(yOffset),
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
)

private suspend fun Animatable<Float, AnimationVector1D>.animateImageScale(
    state: RandomMoviePagerItemType
) = animateTo(
    targetValue = state.toImageScaleValue(),
    animationSpec = tween(durationMillis = 300)
)

private fun RandomMoviePagerItemType.toImageOffsetValue(imageHeightPx: Float) = when (this) {
    RandomMoviePagerItemType.PAGER_ITEM -> 0f
    RandomMoviePagerItemType.BOTTOM_SHEET_ITEM -> -imageHeightPx
}

private fun RandomMoviePagerItemType.toImageScaleValue() = when (this) {
    RandomMoviePagerItemType.PAGER_ITEM -> 1f
    RandomMoviePagerItemType.BOTTOM_SHEET_ITEM -> 0f
}