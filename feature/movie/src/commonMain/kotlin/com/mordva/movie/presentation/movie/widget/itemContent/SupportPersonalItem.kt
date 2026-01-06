package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.listItem.SupportPersonCard
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.supportPersonalItem(
    supportPersonal: List<PersonMovie>,
    onShowAllPersons: () -> Unit,
    onClick: (PersonMovie) -> Unit,
) {
    if (supportPersonal.isEmpty()) return

    item(key = 8) {
        TitleRow(
            title = stringResource(Strings.SupportPersonal),
            onClick = onShowAllPersons
        )

        DefaultLazyRow(
            list = supportPersonal.take(10),
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
    }
}