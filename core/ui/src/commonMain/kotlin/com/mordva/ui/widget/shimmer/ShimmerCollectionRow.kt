package com.mordva.ui.widget.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mordva.ui.theme.DsSpacer

@Composable
fun ShimmerCollectionRow() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(DsSpacer.M10),
        contentPadding = PaddingValues(horizontal = DsSpacer.M16)
    ) {
        items(3) {
            ShimmerCollectionCard()
        }
    }
}

@Composable
fun ShimmerCollectionCard() {
    Box(
        modifier = Modifier
            .size(180.dp)
            .clip(RoundedCornerShape(DsSpacer.M10))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(DsSpacer.M10))
                    .weight(5f)
                    .shimmerEffect()
            )

            ShimmerText(modifier = Modifier.padding(vertical = DsSpacer.M10))
        }
    }
}