package com.mordva.movie.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.getString

internal suspend fun SnackbarHostState.handleSnackBarSate(isChecked: Boolean) {
    val text = if (isChecked)
        getString(Resources.Strings.DeleteFromWillWatch)
    else
        getString(Resources.Strings.AddInWillWatch)

    showSnackbar(
        message = text,
        duration = SnackbarDuration.Short
    )
}
