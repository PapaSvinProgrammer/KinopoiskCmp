package com.mordva.ui.util

fun toAspectRatio(height: Int?, width: Int?): Float {
    if (width == null || height == null || width <= 0 || height <= 0) {
        return PosterType.STANDARD.ratio
    }

    return width.toFloat() / height.toFloat()
}