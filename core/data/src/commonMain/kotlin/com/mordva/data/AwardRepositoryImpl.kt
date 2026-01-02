package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.person.NominationAward
import com.mordva.domain.repository.AwardRepository
import com.mordva.network.external.AwardService

internal class AwardRepositoryImpl(
    private val service: AwardService
) : AwardRepository {
    override suspend fun getMovieAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>> {
        return service.getMovieAwardsByFilter(queryParameters).map { it.toDomain() }
    }

    override suspend fun getPersonAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>> {
        return service.getPersonAwardsByFilter(queryParameters).map { it.toDomain() }
    }
}