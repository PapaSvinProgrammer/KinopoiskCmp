package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mordva.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.listItem.SupportPersonCard
import com.mordva.movie.presentation.navigation.GroupPersonRoute
import com.mordva.movie.utils.toScreenObject
import com.mordva.navigation.PersonGraph
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.voiceActorsItem(
    voiceActors: List<PersonMovie>,
    navController: NavController
) {
    if (voiceActors.isEmpty()) return

    item {
        TitleRow(title = stringResource(Resources.Strings.VoiceActors)) {
            navController.navigate(
                GroupPersonRoute(voiceActors.toScreenObject())
            ) { launchSingleTop = true }
        }

        DefaultLazyRow(
            list = voiceActors.take(10),
            key = { it.id },
            lastItemCard = {
                LastItemCard(
                    width = 180.dp,
                    height = 100.dp
                )
            }
        ) {
            SupportPersonCard(
                person = it,
                onClick = {
                    navController.navigate(PersonGraph.PersonRoute(it.id)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}