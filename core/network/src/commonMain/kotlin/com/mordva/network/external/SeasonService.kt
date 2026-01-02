package com.mordva.network.external

import com.mordva.network.external.model.season.SeasonDto

interface SeasonService {
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<SeasonDto>>
}