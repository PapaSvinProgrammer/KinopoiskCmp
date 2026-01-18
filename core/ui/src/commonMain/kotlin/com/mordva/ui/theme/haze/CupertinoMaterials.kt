package com.mordva.ui.theme.haze

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint

//https://github.com/chrisbanes/haze/blob/main/haze-materials/build.gradle.kts
object CupertinoMaterials {

    /**
     * A [HazeStyle] which implements a mostly translucent material.
     */
    @Composable
    @ReadOnlyComposable
    fun ultraThin(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightBackgroundColor = Color(0xFF0D0D0D),
        lightForegroundColor = Color(color = 0xBFBFBF, alpha = 0.44f),
        darkBackgroundColor = Color(0xFF9C9C9C),
        darkForegroundColor = Color(color = 0x252525, alpha = 0.55f),
    )

    /**
     * A [HazeStyle] which implements a translucent material. More opaque than [ultraThin],
     * more translucent than [regular].
     */
    @Composable
    @ReadOnlyComposable
    fun thin(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightBackgroundColor = Color(0xFF333333),
        lightForegroundColor = Color(color = 0xA6A6A6, alpha = 0.7f),
        darkBackgroundColor = Color(0xFF9C9C9C),
        darkForegroundColor = Color(color = 0x252525, alpha = 0.7f),
    )

    /**
     * A [HazeStyle] which implements a somewhat opaque material. More opaque than [thin],
     * more translucent than [thick].
     */
    @Composable
    @ReadOnlyComposable
    fun regular(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightBackgroundColor = Color(0xFF383838),
        lightForegroundColor = Color(color = 0xB3B3B3, alpha = 0.82f),
        darkBackgroundColor = Color(0xFF8C8C8C),
        darkForegroundColor = Color(color = 0x252525, alpha = 0.82f),
    )

    /**
     * A [HazeStyle] which implements a mostly opaque material. More opaque than [regular].
     */
    @Composable
    @ReadOnlyComposable
    fun thick(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightBackgroundColor = Color(0xFF5C5C5C),
        lightForegroundColor = Color(color = 0x999999, alpha = 0.97f),
        darkBackgroundColor = Color(0xFF7C7C7C),
        darkForegroundColor = Color(color = 0x252525, alpha = 0.9f),
    )

    @ReadOnlyComposable
    @Composable
    private fun hazeMaterial(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        isDark: Boolean = containerColor.luminance() < 0.5f,
        lightBackgroundColor: Color,
        lightForegroundColor: Color,
        darkBackgroundColor: Color,
        darkForegroundColor: Color,
    ): HazeStyle = HazeStyle(
        blurRadius = 24.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
        tints = listOf(
            HazeTint(
                color = if (isDark) darkBackgroundColor else lightBackgroundColor,
                blendMode = if (isDark) BlendMode.Overlay else BlendMode.ColorDodge,
            ),
            HazeTint(color = if (isDark) darkForegroundColor else lightForegroundColor),
        ),
    )
}

private fun Color(color: Int, alpha: Float): Color {
    return Color(color).copy(alpha = alpha)
}