package com.mordva.ui.theme.haze

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint

//https://github.com/chrisbanes/haze/blob/main/haze-materials/build.gradle.kts
object HazeMaterials {

    /**
     * A [HazeStyle] which implements a mostly translucent material.
     */
    @Composable
    @ReadOnlyComposable
    fun ultraThin(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightAlpha = 0.35f,
        darkAlpha = 0.55f,
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
        lightAlpha = 0.6f,
        darkAlpha = 0.65f,
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
        lightAlpha = 0.73f,
        darkAlpha = 0.8f,
    )

    /**
     * A [HazeStyle] which implements a mostly opaque material. More opaque than [regular],
     * more translucent than [ultraThick].
     */
    @Composable
    @ReadOnlyComposable
    fun thick(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightAlpha = 0.83f,
        darkAlpha = 0.9f,
    )

    /**
     * A [HazeStyle] which implements a nearly opaque material.
     */
    @Composable
    @ReadOnlyComposable
    fun ultraThick(
        containerColor: Color = MaterialTheme.colorScheme.surface,
    ): HazeStyle = hazeMaterial(
        containerColor = containerColor,
        lightAlpha = 0.92f,
        darkAlpha = 0.97f,
    )

    private fun hazeMaterial(
        containerColor: Color,
        lightAlpha: Float,
        darkAlpha: Float,
    ): HazeStyle = HazeStyle(
        blurRadius = 24.dp,
        backgroundColor = containerColor,
        tint = HazeTint(
            containerColor.copy(alpha = if (containerColor.luminance() >= 0.5) lightAlpha else darkAlpha),
        ),
    )
}