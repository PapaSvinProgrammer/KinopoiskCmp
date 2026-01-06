package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.component.PersonGridHorizontalList
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.component.TitleRow
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.personGridHorizontalItem(
    actors: List<PersonMovie>,
    onClick: () -> Unit,
    personOnClick: (PersonMovie) -> Unit,
) {
    if (actors.isEmpty()) return

    item(key = 7) {
        TitleRow(
            title = stringResource(Strings.Actors),
            onClick = onClick
        )

        PersonGridHorizontalList(
            list = actors.take(9),
            onClick = personOnClick
        )
    }
}
