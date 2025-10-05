package com.mordva.kinopoiskkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.mordva.kinopoiskkmp.app.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        changeSystemBarStyle(true)
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    private fun changeSystemBarStyle(isDark: Boolean) {
        enableEdgeToEdge(
            statusBarStyle = getSystemBarStyle(isDark),
            navigationBarStyle = getSystemBarStyle(isDark)
        )
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
    return when (isDark) {
        true -> SystemBarStyle.dark(Color.Transparent.toArgb())
        false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
    }
}