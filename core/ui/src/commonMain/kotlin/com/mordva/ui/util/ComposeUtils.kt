package com.mordva.ui.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.measureHeightOnce(onMeasured: (Dp) -> Unit) = composed {
    val density = LocalDensity.current
    val onMeasuredState by rememberUpdatedState(onMeasured)
    var measured by remember { mutableStateOf(false) }

    onGloballyPositioned { coords ->
        if (!measured) {
            measured = true
            onMeasuredState(
                with(density) { coords.size.height.toDp() }
            )
        }
    }
}