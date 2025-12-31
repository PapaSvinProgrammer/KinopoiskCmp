package com.mordva.ui.util

enum class PosterType(val ratio: Float) {
    STANDARD(2f / 3f),
    WIDE(16f / 9f),
    SQUARE(1f),
    IMAX(3f / 4f),
    NETFLIX(7f / 10f),
    COMPACT(3f / 4f),
    ;
}