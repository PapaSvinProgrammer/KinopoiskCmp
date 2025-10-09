package com.mordva.images_list.util

import com.mordva.model.image.ImageType
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.StringResource

internal fun ImageType.toStringResource(): StringResource {
    return when (this) {
        ImageType.ALL -> Resources.Strings.All
        ImageType.BACKDROP -> Resources.Strings.Backdrop
        ImageType.COVER -> Resources.Strings.Cover
        ImageType.FRAME -> Resources.Strings.Frame
        ImageType.PROMO -> Resources.Strings.Promo
        ImageType.SCREENSHOT -> Resources.Strings.Screenshot
        ImageType.SHOOTING -> Resources.Strings.Shooting
        ImageType.STILL -> Resources.Strings.Still
        ImageType.WALLPAPER -> Resources.Strings.Wallpaper
    }
}

internal fun Set<ImageType>.toStringResource() = map { it.toStringResource() }
