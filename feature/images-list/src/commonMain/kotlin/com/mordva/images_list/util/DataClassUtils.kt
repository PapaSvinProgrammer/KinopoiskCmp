package com.mordva.images_list.util

import com.mordva.domain.model.image.Poster
import com.mordva.images_list.presentation.widget.ImageListState

internal fun ImageListState.getData(): List<Poster> {
    return (this as? ImageListState.Success)?.data ?: listOf()
}

internal fun isLongImage(height: Int?, width: Int?): Boolean {
    val normalWidth = width ?: 0
    val normalHeight = height ?: 0
    return normalWidth > normalHeight * 1.7
}