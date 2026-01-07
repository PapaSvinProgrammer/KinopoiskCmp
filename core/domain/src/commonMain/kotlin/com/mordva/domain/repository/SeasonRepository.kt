package com.mordva.domain.repository

import com.mordva.domain.model.season.Season

interface SeasonRepository {
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>>
}