package com.mordva.ui.widget.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mordva.ui.theme.DsSpacer

@Composable
fun ShimmerFactRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(DsSpacer.M10),
        contentPadding = PaddingValues(horizontal = 15.dp)
    ) {
        items(2) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(DsSpacer.M10))
                    .shimmerEffect()
            )
        }
    }
}