package com.mordva.images_list.util

import com.mordva.model.image.Poster
import com.mordva.ui.uiState.ImageListUIState

internal fun ImageListUIState.getData(): List<Poster> {
    return (this as? ImageListUIState.Success)?.data ?: listOf()
}

internal fun isLongImage(height: Int?, width: Int?): Boolean {
    val normalWidth = width ?: 0
    val normalHeight = height ?: 0
    return normalWidth > normalHeight * 1.7
}