package com.mordva.person.presentation.widget.render

import androidx.compose.runtime.Composable
import com.mordva.person.presentation.widget.content.BirthdayDeathContent
import com.mordva.person.presentation.widget.listItem.DetailInfoListItem
import com.mordva.model.person.Person
import com.mordva.ui.theme.Resources
import com.mordva.ui.uiState.PersonUIState
import com.mordva.ui.util.PrettyData
import com.mordva.ui.widget.shimmer.ShimmerPersonDetail
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RenderPersonDetailState(state: PersonUIState) {
    when (state) {
        PersonUIState.Loading -> ShimmerPersonDetail()
        is PersonUIState.Success -> {
            MainPersonDetailContent(person = state.data.first())
        }
    }
}

@Composable
private fun MainPersonDetailContent(person: Person) {
    if (person.professions.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(Resources.Strings.Professions),
            trailing = person.professions.joinToString(", ")
        )
    }

    person.growth?.let {
        DetailInfoListItem(
            header = stringResource(Resources.Strings.Growth),
            trailing = PrettyData.getPrettyGrowth(it)
        )
    }

    BirthdayDeathContent(
        birthday = person.birthday,
        death = person.death,
        age = person.age
    )

    if (person.birthPlace.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(Resources.Strings.BirthPlace),
            trailing = person.birthPlace.joinToString(", ") { it.value }
        )
    }

    if (person.deathPlace.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(Resources.Strings.DeathPlace),
            trailing = person.deathPlace.joinToString(", ")
        )
    }

    person.countAwards?.let {
        DetailInfoListItem(
            header = stringResource(Resources.Strings.CountAwards),
            trailing = it.toString()
        )
    }

    if (person.movies.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(Resources.Strings.TotalMovies),
            trailing = person.movies.size.toString()
        )
    }
}