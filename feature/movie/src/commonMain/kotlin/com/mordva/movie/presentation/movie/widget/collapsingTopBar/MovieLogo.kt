package com.mordva.movie.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun MovieLogo(url: String?, name: String) {
    if (url == null) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        return
    }

    when (url.isSvg()) {
        true -> RenderSvg(url)
        false -> RenderPng(url)
    }
}

@Composable
private fun RenderPng(url: String?) {
    AsyncImage(
        model = url,
        error = painterResource(Resources.Icons.Movie),
        contentDescription = null,
        modifier = Modifier.padding(horizontal = 55.dp)
    )
}

@Composable
private fun RenderSvg(url: String?) {
    AsyncImage(
        model = url,
        error = painterResource(Resources.Icons.Movie),
        contentDescription = null,
        modifier = Modifier.padding(horizontal = 55.dp)
    )
}

private fun String.isSvg(): Boolean {
    return this.dropLast(3) == "svg"
}