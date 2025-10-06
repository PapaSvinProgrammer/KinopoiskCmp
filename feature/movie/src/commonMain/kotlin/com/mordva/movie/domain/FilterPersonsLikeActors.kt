package com.mordva.movie.domain

import com.mordva.model.person.PersonMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

internal class FilterPersonsLikeActors(
) : UseCase<List<PersonMovie>, List<PersonMovie>>(Dispatchers.Default) {
    override suspend fun run(params: List<PersonMovie>): List<PersonMovie> {
        return params.filter { it.enProfession == "actor" }
    }
}
