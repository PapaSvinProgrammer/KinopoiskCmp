package com.mordva.movie.domain.handle

import com.mordva.domain.model.local.PackageParams
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.movie.domain.model.CheckedParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class HandleWillWatchAction(
    private val repository: WillWatchPackageRepository
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
