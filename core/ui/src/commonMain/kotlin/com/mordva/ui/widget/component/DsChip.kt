package com.mordva.ui.widget.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize

@Composable
fun DsChip(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(DsSpacer.M8))
            .border(
                width = DsSpacer.M1,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(DsSpacer.M20)
            )
            .padding(vertical = DsSpacer.M6, horizontal = DsSpacer.M12)
    ) {
        Text(
            text = title,
            fontSize = DsTextSize.M12,
            lineHeight = DsTextSize.M10,
        )
    }
}