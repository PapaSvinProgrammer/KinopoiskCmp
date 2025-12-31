package com.mordva.sqlite.internal.utils

import com.mordva.model.local.History
import com.mordva.sqlite.internal.entities.history.HistoryEntity

internal fun History.toEntity(): HistoryEntity = HistoryEntity(
    id = id,
    movieId = movieId,
    name = name,
    alternativeName = alternativeName,
    year = year,
    start = start,
    end = end,
    poster = poster,
    isMovie = isMovie
)

internal fun HistoryEntity.toDomain(): History = History(
    id = id,
    movieId = movieId,
    name = name,
    alternativeName = alternativeName,
    year = year,
    start = start,
    end = end,
    poster = poster,
    isMovie = isMovie
)