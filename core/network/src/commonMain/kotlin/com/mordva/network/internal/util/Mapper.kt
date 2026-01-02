package com.mordva.network.internal.util

import com.mordva.network.external.model.image.ImageTypeKtor

internal fun ImageTypeKtor.toKtorString(): String {
    return when (this) {
        ImageTypeKtor.ALL -> ""
        ImageTypeKtor.BACKDROP -> "backdrops"
        ImageTypeKtor.COVER -> "cover"
        ImageTypeKtor.FRAME -> "frame"
        ImageTypeKtor.PROMO -> "promo"
        ImageTypeKtor.SCREENSHOT -> "screenshot"
        ImageTypeKtor.SHOOTING -> "shooting"
        ImageTypeKtor.STILL -> "still"
        ImageTypeKtor.WALLPAPER -> "wallpaper"
    }
}