package com.mordva.ui.widget.packageBottomSheet

import com.mordva.model.PackageType
import com.mordva.ui.theme.Icons
import org.jetbrains.compose.resources.DrawableResource

internal fun PackageType.toIcon(isSelected: Boolean): DrawableResource {
    return when (this) {
        PackageType.FAVORITE -> {
            if (isSelected)
                Icons.FolderStarFill
            else
                Icons.FolderStar
        }

        PackageType.WILL_WATCH -> {
            if (isSelected)
                Icons.FolderEyeFill
            else
                Icons.FolderEye
        }
    }
}