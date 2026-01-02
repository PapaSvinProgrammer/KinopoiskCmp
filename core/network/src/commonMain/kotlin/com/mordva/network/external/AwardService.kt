package com.mordva.network.external

import com.mordva.network.external.model.person.NominationAwardDto

interface AwardService {
    suspend fun getPersonAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAwardDto>>

    suspend fun getMovieAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAwardDto>>
}