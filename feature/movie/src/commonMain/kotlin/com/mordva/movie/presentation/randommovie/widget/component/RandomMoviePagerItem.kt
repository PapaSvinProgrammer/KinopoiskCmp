package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.listComponent.randomMovieAnimatedRating
import com.mordva.movie.presentation.randommovie.widget.listComponent.randomMovieGenresRow
import com.mordva.movie.presentation.randommovie.widget.listComponent.randomMoviePosterImage
import com.mordva.movie.presentation.randommovie.widget.listComponent.randomMovieTitle
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.customOffset
import com.mordva.ui.util.measureWidthOnce
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.TextTitleRow
import com.mordva.ui.widget.component.fadingEdge
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RandomMoviePagerItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    state: RandomMoviePagerItemState,
) {
    val imageScale = remember { Animatable(1f) }
    val titleOffsetY = remember { Animatable(0f) }
    val starOffsetsY = remember { List(5) { Animatable(0f) } }
    val personOffsetsY = remember { List(10) { Animatable(0f) } }

    var imageHeightPx by remember { mutableStateOf(0f) }
    var imageWidth by remember { mutableStateOf(0.dp) }

    LaunchedEffect(state) {
        imageScale.animateImageScale(state)

        launch {
            titleOffsetY.animateTitleOffset(state, imageHeightPx)
        }

        starOffsetsY.forEachIndexed { index, animatable ->
            launch {
                delay(index * 60L)
                animatable.animateOffsetYWithDelay(state, imageHeightPx)
            }
        }

        personOffsetsY.forEachIndexed { index, animatable ->
            launch {
                delay(index * 60L)
                animatable.animateOffsetYWithDelay(state, imageHeightPx)
            }
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DsSpacer.M16),
        modifier = modifier
            .fadingEdge(FadingDefaults.bottomFade)
            .clip(RoundedCornerShape(60.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .measureWidthOnce { imageWidth = it }
    ) {
        randomMoviePosterImage(
            imageUrl = movie.poster?.url.toString(),
            width = imageWidth,
            scale = imageScale.value,
            onPositioned = {
                imageHeightPx = it.size.height.toFloat()
            }
        )

        randomMovieTitle(
            title = movie.name.toString(),
            offsetY = titleOffsetY.value
        )

        randomMovieGenresRow(
            genres = movie.genres.map { it.name },
            offsetY = titleOffsetY.value
        )

        randomMovieAnimatedRating(
            rating = movie.rating?.kp ?: 0f,
            starOffsets = starOffsetsY
        )

//        movieDescriptionItem(movie)

        randomMoviePersonList(
            title = Strings.Persons,
            list = movie.persons.take(10), // Использовать <= 10, тк внутри обычный row
            yOffset = personOffsetsY,
            onAction = { },
        )
    }
}

internal fun LazyListScope.randomMoviePersonList(
    title: StringResource,
    list: List<PersonMovie>,
    yOffset: List<Animatable<Float, AnimationVector1D>>,
    onAction: (RandomMovieAction) -> Unit,
) {
    if (list.isEmpty()) return

    item(key = 8) {
        TextTitleRow(
            title = stringResource(title),
            fontSize = DsTextSize.M14,
            fontWeight = FontWeight.Medium,
            onClick = { onAction(RandomMovieAction.ShowAllPersonCLicked) },
            modifier = Modifier.customOffset(yOffset = yOffset.first().value)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(DsSpacer.M10),
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = DsSpacer.M16)
        ) {
            list.forEachIndexed { index, person ->
                PersonMovieSquare(
                    name = person.name.toString(),
                    image = person.photo.toString(),
                    role = person.profession.toString(),
                    onClick = { onAction(RandomMovieAction.PersonClicked(person.id)) },
                    modifier = Modifier.graphicsLayer {
                        translationY = yOffset[index].value
                    }

//                    customOffset(yOffset = yOffset[index].value)
                )
            }
        }
    }
}

private suspend fun Animatable<Float, AnimationVector1D>.animateOffsetYWithDelay(
    state: RandomMoviePagerItemState,
    yOffset: Float,
) = animateTo(
    targetValue = state.toImageOffsetValue(yOffset),
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
)

private suspend fun Animatable<Float, AnimationVector1D>.animateImageScale(
    state: RandomMoviePagerItemState
) = animateTo(
    targetValue = state.toImageScaleValue()
)

private suspend fun Animatable<Float, AnimationVector1D>.animateTitleOffset(
    state: RandomMoviePagerItemState,
    imageHeightPx: Float,
) = animateTo(
    targetValue = state.toImageOffsetValue(imageHeightPx)
)

private fun RandomMoviePagerItemState.toImageOffsetValue(imageHeightPx: Float) = when (this) {
    RandomMoviePagerItemState.PAGER_ITEM -> 0f
    RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> -imageHeightPx
}

private fun RandomMoviePagerItemState.toImageScaleValue() = when (this) {
    RandomMoviePagerItemState.PAGER_ITEM -> 1f
    RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> 0f
}