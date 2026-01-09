package com.mordva.ui.widget.lazyComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.mordva.ui.theme.DsSpacer

@Composable
fun <T> AdaptiveRow(
    items: List<T>,
    minItemWidth: Dp,
    modifier: Modifier = Modifier,
    spacing: Dp = DsSpacer.ZERO,
    scaleThreshold: Float = 0.85f,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(spacing),
    itemContent: @Composable (item: T, index: Int, itemWidth: Dp) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        var count = (maxWidth / (minItemWidth + spacing))
            .toInt()
            .coerceAtLeast(1)

        val nextItemWidth = itemWidthFor(maxWidth, spacing, count + 1)
        if (nextItemWidth >= minItemWidth * scaleThreshold) {
            count++
        }

        val itemWidth = itemWidthFor(maxWidth, spacing, count)

        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = Modifier.fillMaxWidth()
        ) {
            items
                .take(count)
                .forEachIndexed { index, item ->
                    itemContent(item, index, itemWidth)
                }
        }
    }
}

private fun itemWidthFor(maxWidthDp: Dp, spacing: Dp, count: Int): Dp {
    return (maxWidthDp - spacing * (count - 1)) / count
}
