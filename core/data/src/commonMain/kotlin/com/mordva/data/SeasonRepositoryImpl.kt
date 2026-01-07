package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.season.Season
import com.mordva.domain.repository.SeasonRepository
import com.mordva.network.external.SeasonService

internal class SeasonRepositoryImpl(
    private val service: SeasonService
) : SeasonRepository {
    override suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>> {
        return service.getSeasonsByMovie(movieId).map { list ->
            list.map { it.toDomain() }
        }
    }
}