package com.mordva.images_list.presentation.widget

import com.mordva.domain.model.image.ImageType

internal data class ImageListUIState(
    val page: Int = 1,
    val imagesState: ImageListState = ImageListState.Loading,
    val imageTypes: Set<ImageType> = setOf(ImageType.ALL)
)