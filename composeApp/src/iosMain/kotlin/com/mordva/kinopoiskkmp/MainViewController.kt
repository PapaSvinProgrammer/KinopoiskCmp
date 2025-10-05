package com.mordva.kinopoiskkmp

import androidx.compose.ui.window.ComposeUIViewController
import com.mordva.kinopoiskkmp.app.App
import com.mordva.kinopoiskkmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}