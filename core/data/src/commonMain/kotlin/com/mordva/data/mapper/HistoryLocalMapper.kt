package com.mordva.data.mapper

import com.mordva.domain.model.local.History
import com.mordva.sqlite.entities.history.HistoryEntity

internal fun History.toWillWatchPackageEntity(): HistoryEntity = HistoryEntity(
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