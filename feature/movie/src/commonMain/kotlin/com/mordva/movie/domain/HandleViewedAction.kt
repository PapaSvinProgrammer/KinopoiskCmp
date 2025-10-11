package com.mordva.movie.domain

import com.mordva.domain.repository.ViewedRepository
import com.mordva.model.local.PackageParams
import com.mordva.movieScreen.domain.model.CheckedParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class HandleViewedAction(
    private val repository: ViewedRepository
) : UseCase<CheckedParams, Unit>(Dispatchers.IO) {
    @OptIn(ExperimentalTime::class)
    override suspend fun run(params: CheckedParams) {
        val packageParams = PackageParams(
            id = params.movieId,
            date = Clock.System.now().toEpochMilliseconds()
        )

        when (params.isChecked) {
            true -> repository.delete(params.movieId)
            false -> repository.insert(packageParams)
        }
    }
}