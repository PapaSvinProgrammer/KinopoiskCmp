package com.mordva.ui.widget.packageBottomSheet

import com.mordva.model.PackageType
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.DrawableResource

internal fun PackageType.toIcon(isSelected: Boolean): DrawableResource {
    return when (this) {
        PackageType.FAVORITE -> {
            if (isSelected)
                Resources.Icons.FolderStarFill
            else
                Resources.Icons.FolderStar
        }

        PackageType.WILL_WATCH -> {
            if (isSelected)
                Resources.Icons.FolderEyeFill
            else
                Resources.Icons.FolderEye
        }
    }
}