package com.mordva.person.domain

import com.mordva.domain.model.movie.ShortMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

class GroupShortMovie(
) : UseCase<List<ShortMovie>, Map<String, List<ShortMovie>>>(Dispatchers.Default) {
    override suspend fun run(params: List<ShortMovie>): Map<String, List<ShortMovie>> {
        return params.groupBy { it.enProfession ?: "" }
    }
}