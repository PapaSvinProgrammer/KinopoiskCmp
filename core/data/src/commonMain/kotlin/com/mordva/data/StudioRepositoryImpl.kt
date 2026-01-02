package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.movie.Studio
import com.mordva.domain.repository.StudioRepository
import com.mordva.network.external.StudiesService

internal class StudioRepositoryImpl(
    private val service: StudiesService
) : StudioRepository {
    override suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>> {
        return service.getStudies(queryParameters).map { it.toDomain() }
    }
}