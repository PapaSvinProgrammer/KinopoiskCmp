package com.mordva.ui.util

import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.IntOffset
import com.mordva.ui.theme.DsSpacer
import kotlin.math.roundToInt

fun Modifier.measureHeightOnce(onMeasured: (Dp) -> Unit) = composed {
    val density = LocalDensity.current
    val onMeasuredState by rememberUpdatedState(onMeasured)
    var height by remember { mutableStateOf<Dp?>(null) }

    onGloballyPositioned { coords ->
        if (height == null) {
            height = with(density) { coords.size.height.toDp() }
            onMeasuredState(height ?: DsSpacer.ZERO)
        }
    }
}

fun Modifier.measureWidthOnce(onMeasured: (Dp) -> Unit) = composed {
    val density = LocalDensity.current
    val onMeasuredState by rememberUpdatedState(onMeasured)
    var height by remember { mutableStateOf<Dp?>(null) }

    onGloballyPositioned { coords ->
        if (height == null) {
            height = with(density) { coords.size.width.toDp() }
            onMeasuredState(height ?: DsSpacer.ZERO)
        }
    }
}

fun Modifier.customOffset(xOffset: Float = 0f, yOffset: Float) = offset {
    IntOffset(
        x = xOffset.roundToInt(),
        y = yOffset.roundToInt()
    )
}