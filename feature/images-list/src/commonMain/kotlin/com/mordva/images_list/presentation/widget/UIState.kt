package com.mordva.images_list.presentation.widget

import com.mordva.model.image.ImageType
import com.mordva.ui.uiState.ImageListUIState

internal data class UIState(
    val page: Int = 1,
    val imagesState: ImageListUIState = ImageListUIState.Loading,
    val imageTypes: Set<ImageType> = setOf(ImageType.ALL)
)