package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize

@Composable
internal fun RandomMovieDirectorTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Director / $text",
        fontSize = DsTextSize.M12,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(horizontal = DsSpacer.M10)
    )
}