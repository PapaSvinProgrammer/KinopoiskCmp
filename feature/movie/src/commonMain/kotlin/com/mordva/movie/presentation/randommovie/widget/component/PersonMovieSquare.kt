package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.RoundedCorners
import com.mordva.ui.util.PosterType
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun PersonMovieSquare(
    name: String,
    image: String,
    role: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        AsyncImage(
            model = image,
            error = painterResource(Icons.Face),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(Icons.Movie),
            modifier = Modifier
                .width(150.dp)
                .aspectRatio(PosterType.SQUARE.ratio)
                .clip(RoundedCorners.M10)
        )

        Text(
            text = name,
            fontWeight = FontWeight.Light,
            fontSize = DsTextSize.M14,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            lineHeight = DsTextSize.M10,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Text(
            text = role,
            fontWeight = FontWeight.Light,
            fontSize = DsTextSize.M14,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            lineHeight = DsTextSize.M10,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}