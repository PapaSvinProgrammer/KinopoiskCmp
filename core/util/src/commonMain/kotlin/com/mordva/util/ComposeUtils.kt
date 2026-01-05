package com.mordva.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}

@Composable
fun windowHeightPercent(percent: Float = 1f): Dp {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    return with(density) {
        (windowInfo.containerSize.height * percent).toDp()
    }
}

@Composable
fun windowWidthPercent(percent: Float = 1f): Dp {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    return with(density) {
        (windowInfo.containerSize.width * percent).toDp()
    }
}
