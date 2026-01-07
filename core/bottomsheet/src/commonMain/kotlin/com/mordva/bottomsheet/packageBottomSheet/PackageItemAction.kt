package com.mordva.bottomsheet.packageBottomSheet

import com.mordva.domain.model.PackageType

sealed interface PackageItemAction {
    data class Add(val type: PackageType) : PackageItemAction
    data class Delete(val type: PackageType) : PackageItemAction
}