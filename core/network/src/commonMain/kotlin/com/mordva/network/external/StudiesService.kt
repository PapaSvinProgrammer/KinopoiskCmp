package com.mordva.network.external

import com.mordva.network.external.model.movie.StudioDto

interface StudiesService {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<StudioDto>>
}