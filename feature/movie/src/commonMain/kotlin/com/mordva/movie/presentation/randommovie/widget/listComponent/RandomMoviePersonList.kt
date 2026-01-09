package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.component.PersonMovieSquare
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.util.customOffset
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.AdaptiveRow
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.randomMoviePersonList(
    title: StringResource,
    list: List<PersonMovie>,
    yOffset: List<Animatable<Float, AnimationVector1D>>,
    onAction: (RandomMovieAction) -> Unit,
) {
    if (list.isEmpty()) return

    item(key = 8) {
        TitleRow(
            title = stringResource(title),
            fontSize = DsTextSize.M14,
            fontWeight = FontWeight.Medium,
            onClick = { onAction(RandomMovieAction.ShowAllPersonCLicked) },
            contentPadding = PaddingValues(
                vertical = DsSpacer.M12,
                horizontal = DsSpacer.M10
            ),
            modifier = Modifier.customOffset(yOffset = yOffset.first().value)
        )

        AdaptiveRow(
            items = list,
            minItemWidth = 120.dp,
            spacing = DsSpacer.M10,
            modifier = Modifier.padding(horizontal = DsSpacer.M10)
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