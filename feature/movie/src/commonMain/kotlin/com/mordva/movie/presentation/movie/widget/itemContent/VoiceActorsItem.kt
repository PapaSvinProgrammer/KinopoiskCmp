package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.listItem.SupportPersonCard
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.voiceActorsItem(
    voiceActors: List<PersonMovie>,
    onShowAllPersons: () -> Unit,
    onClick: (PersonMovie) -> Unit,
) {
    if (voiceActors.isEmpty()) return

    item {
        TitleRow(
            title = stringResource(Resources.Strings.VoiceActors),
            onClick = onShowAllPersons
        )

        DefaultLazyRow(
            list = voiceActors.take(10),
            lastItemCard = {
                LastItemCard(
                    width = 180.dp,
                    height = 100.dp
                )
            }
        ) {
            SupportPersonCard(
                person = it,
                onClick = { onClick(it) }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}