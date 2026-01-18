package com.mordva.ui.theme.haze

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp

// HazeBorder нужно обязательно использорвать перед hazeEffect, иначе появятся артифакты

@Composable
fun Modifier.hazeBorder() = clip(CircleShape)
    .border(
        width = Dp.Hairline,
        brush = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.onSurface.copy(alpha = .8f),
                MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),
            ),
        ),
        shape = CircleShape
    )