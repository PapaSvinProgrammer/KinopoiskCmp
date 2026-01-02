package com.mordva.images_list.presentation.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.image.Poster

@Immutable
sealed interface ImageListState {
    data object Loading : ImageListState
    data class Success(val data: List<Poster>) : ImageListState
}