package com.mordva.movie.domain.model

import com.mordva.bottomsheet.packageBottomSheet.PackageItemAction

internal data class PackageItemParams(
    val action: PackageItemAction,
    val movieId: Int
)