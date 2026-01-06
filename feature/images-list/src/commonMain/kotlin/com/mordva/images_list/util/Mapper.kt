package com.mordva.images_list.util

import com.mordva.model.image.ImageType
import com.mordva.ui.theme.Strings
import org.jetbrains.compose.resources.StringResource

internal fun ImageType.toStringResource(): StringResource {
    return when (this) {
        ImageType.ALL -> Strings.All
        ImageType.BACKDROP -> Strings.Backdrop
        ImageType.COVER -> Strings.Cover
        ImageType.FRAME -> Strings.Frame
        ImageType.PROMO -> Strings.Promo
        ImageType.SCREENSHOT -> Strings.Screenshot
        ImageType.SHOOTING -> Strings.Shooting
        ImageType.STILL -> Strings.Still
        ImageType.WALLPAPER -> Strings.Wallpaper
    }
}

internal fun Set<ImageType>.toStringResource() = map { it.toStringResource() }
