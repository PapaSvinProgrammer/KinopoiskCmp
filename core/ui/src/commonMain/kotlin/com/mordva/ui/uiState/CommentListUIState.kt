package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.movie.Comment

@Immutable
sealed interface CommentListUIState {
    data class Success(val data: List<Comment>) : CommentListUIState
    data object Loading : CommentListUIState
}