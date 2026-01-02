package com.mordva.movie.domain

import com.mordva.domain.model.person.PersonMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

internal class FilterPersonsLikeSupport(
) : UseCase<List<PersonMovie>, List<PersonMovie>>(Dispatchers.Default) {
    override suspend fun run(params: List<PersonMovie>): List<PersonMovie> {
        return params.filter {
            it.enProfession != "actor" && it.enProfession != "voice_actor"
        }
    }
}
