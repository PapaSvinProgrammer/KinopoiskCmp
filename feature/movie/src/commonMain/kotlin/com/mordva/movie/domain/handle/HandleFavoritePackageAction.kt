package com.mordva.movie.domain.handle

import com.mordva.bottomsheet.packageBottomSheet.PackageItemAction
import com.mordva.domain.model.PackageType
import com.mordva.domain.model.local.PackageParams
import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.movie.domain.model.PackageItemParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class HandleFavoritePackageAction(
    private val willWatchPackageRepository: WillWatchPackageRepository,
    private val favoritePackageRepository: FavoritePackageRepository
) : UseCase<PackageItemParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: PackageItemParams) {
        when (params.action) {
            is PackageItemAction.Add -> addByType(params.action.type, params.movieId)
            is PackageItemAction.Delete -> deleteByType(params.action.type, params.movieId)
        }
    }

    @OptIn(ExperimentalTime::class)
    private suspend fun addByType(type: PackageType, id: Int) {
        val params = PackageParams(
            id = id,
            date = Clock.System.now().toEpochMilliseconds()
        )

        when (type) {
            PackageType.FAVORITE -> favoritePackageRepository.insert(params)
            PackageType.WILL_WATCH -> willWatchPackageRepository.insert(params)
        }
    }

    private suspend fun deleteByType(type: PackageType, id: Int) {
        when (type) {
            PackageType.FAVORITE -> favoritePackageRepository.delete(id)
            PackageType.WILL_WATCH -> willWatchPackageRepository.delete(id)
        }
    }
}