package com.mordva.network.internal.service

import com.mordva.network.external.SeasonService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.season.SeasonDto
import com.mordva.util.Constants.LIMIT_FIELD
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.SORT_FIELD
import com.mordva.util.Constants.SORT_TYPE
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class SeasonServiceImpl(
    private val client: HttpClient
) : SeasonService {
    override suspend fun getSeasonsByMovie(movieId: Int): Result<List<SeasonDto>> {
        return safeCall<Docs<SeasonDto>> {
            client.get("v1.4/season") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(MOVIE_ID_FIELD, movieId.toString())
                    parameters.append(SORT_FIELD, "number")
                    parameters.append(SORT_TYPE, "1")
                }
            }
        }.map { doc -> doc.docs }
    }
}