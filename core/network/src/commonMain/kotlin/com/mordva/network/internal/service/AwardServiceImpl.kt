package com.mordva.network.internal.service

import com.mordva.network.external.AwardService
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.person.NominationAwardDto
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.util.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class AwardServiceImpl(
    private val client: HttpClient
) : AwardService {
    override suspend fun getPersonAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAwardDto>> {
        return safeCall<Docs<NominationAwardDto>> {
            client.get("v1.4/person/awards") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { it.docs }
    }

    override suspend fun getMovieAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAwardDto>> {
        return safeCall<Docs<NominationAwardDto>> {
            client.get("v1.4/movie/awards") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { it.docs }
    }
}