package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.fadingEdge

@Composable
internal fun RandomMovieDescription(
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = DsSpacer.M16)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .fadingEdge(FadingDefaults.bottomFade)
    ) {
        Text(
            text = description,
            fontSize = DsTextSize.M12,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}