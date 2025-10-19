package com.mordva.ui.util

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade

@Composable
@OptIn(ExperimentalCoilApi::class)
fun InitCoil() {
    setSingletonImageLoaderFactory { context: PlatformContext ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }
}