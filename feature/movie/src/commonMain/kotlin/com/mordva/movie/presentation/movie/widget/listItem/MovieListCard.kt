package com.mordva.movie.presentation.movie.widget.listItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import com.mordva.ui.util.ConvertData
import com.mordva.ui.widget.chips.RatingChip
import com.mordva.ui.widget.listItems.poster.StandardImageMedium
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieListCard(
    movie: Movie,
    onClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        Box {
            StandardImageMedium(movie.poster?.url)

            RatingChip(
                modifier = Modifier.padding(5.dp),
                rating = movie.rating?.kp ?: 0f,
                top = movie.top250,
                fontSize = Typography.bodySmall.fontSize
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Box(
            modifier = Modifier
                .height(130.dp)
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                NameContent(movie)
                DetailInfoContent(movie)
            }

            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onSettingsClick
            ) {
                Icon(
                    painter = painterResource(Resources.Icons.MoreVert),
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
            }
        }
    }
}

@Composable
private fun NameContent(movie: Movie) {
    Text(
        text = movie.name ?: "",
        fontSize = Typography.bodyMedium.fontSize,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )

    Text(
        text = getAlternativeName(movie),
        fontSize = Typography.bodySmall.fontSize,
        fontWeight = FontWeight.Medium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

private fun getAlternativeName(movie: Movie): String {
    var res = ""

    movie.alternativeName?.let {
        if (it.isNotEmpty()) res += "$it, "
    }

    res += ConvertData.convertDateCreated(
        year = movie.year,
        start = movie.releaseYears.firstOrNull()?.start,
        end = movie.releaseYears.firstOrNull()?.end,
    )

    return res
}

@Composable
private fun DetailInfoContent(movie: Movie) {
    Text(
        text = movie.countries.joinToString(", ") { it.name },
        fontSize = Typography.bodySmall.fontSize,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )

    Text(
        text = movie.genres.joinToString(", ") { it.name },
        fontSize = Typography.bodySmall.fontSize,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}