package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.util.customOffset

internal fun LazyListScope.randomMovieTitle(title: String, offsetY: Float) {
    item {
        Text(
            text = title,
            fontSize = DsTextSize.M22,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = DsSpacer.M10)
                .customOffset(yOffset = offsetY)
        )
    }
}