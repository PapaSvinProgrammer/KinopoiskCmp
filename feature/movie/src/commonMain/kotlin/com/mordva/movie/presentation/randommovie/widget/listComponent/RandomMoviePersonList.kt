package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.component.PersonMovieSquare
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.util.customOffset
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.AdaptiveRow
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RandomMoviePersonList(
    title: StringResource,
    list: List<PersonMovie>,
    yOffset: List<Animatable<Float, AnimationVector1D>>,
    onAction: (RandomMovieAction) -> Unit,
) {
    if (list.isEmpty()) return

    Column {
        TitleRow(
            title = stringResource(title),
            onClick = { onAction(RandomMovieAction.ShowAllPersonClicked) },
            modifier = Modifier.customOffset(yOffset = yOffset.first().value)
        )

        AdaptiveRow(
            items = list,
            minItemWidth = 120.dp,
            spacing = DsSpacer.M10,
            modifier = Modifier.padding(horizontal = DsSpacer.M16)
        ) { person, index, itemWidth ->
            PersonMovieSquare(
                name = person.name.orEmpty(),
                image = person.photo.orEmpty(),
                role = person.profession.orEmpty(),
                onClick = { onAction(RandomMovieAction.PersonClicked(person.id)) },
                modifier = Modifier
                    .width(itemWidth)
                    .graphicsLayer {
                        translationY = yOffset[index].value
                    }
            )
        }
    }
}