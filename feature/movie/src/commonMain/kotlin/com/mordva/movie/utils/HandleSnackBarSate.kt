package com.mordva.movie.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.mordva.ui.theme.Strings
import org.jetbrains.compose.resources.getString

internal suspend fun SnackbarHostState.handleSnackBarSate(isChecked: Boolean) {
    val text = if (isChecked)
        getString(Strings.DeleteFromWillWatch)
    else
        getString(Strings.AddInWillWatch)

    showSnackbar(
        message = text,
        duration = SnackbarDuration.Short
    )
}
