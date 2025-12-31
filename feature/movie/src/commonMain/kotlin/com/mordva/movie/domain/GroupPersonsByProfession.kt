package com.mordva.movie.domain

import com.mordva.movie.domain.model.PersonMovieExtended
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

internal class GroupPersonsByProfession(
) : UseCase<List<PersonMovieExtended>, Map<String, List<PersonMovieExtended>>>(Dispatchers.Default) {
    override suspend fun run(params: List<PersonMovieExtended>): Map<String, List<PersonMovieExtended>> {
        return params.groupBy { it.enProfession ?: " " }
    }
}
