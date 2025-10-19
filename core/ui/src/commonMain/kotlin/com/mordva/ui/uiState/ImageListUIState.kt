package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.image.Poster

@Immutable
sealed interface ImageListUIState {
    data object Loading : ImageListUIState
    data class Success(val data: List<Poster>) : ImageListUIState
}