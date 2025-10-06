package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
import androidx.navigation.NavController
import com.mordva.movie.presentation.movie.widget.component.PersonGridHorizontalList
import com.mordva.model.person.PersonMovie
import com.mordva.navigation.PersonGraph
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.TitleRow
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.personGridHorizontalItem(
    actors: List<PersonMovie>,
    navController: NavController,
    onClick: () -> Unit
) {
    if (actors.isEmpty()) return

    item {
        TitleRow(
            title = stringResource(Resources.Strings.Actors),
            onClick = onClick
        )

        PersonGridHorizontalList(
            list = actors.take(9),
            onClick = {
                navController.navigate(PersonGraph.PersonRoute(it.id)) {
                    launchSingleTop = true
                }
            }
        )
    }
}
