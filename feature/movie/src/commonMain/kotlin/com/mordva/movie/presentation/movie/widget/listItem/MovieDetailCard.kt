package com.mordva.movie.presentation.movie.widget.listItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.category.ItemName
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.theme.Typography
import com.mordva.ui.util.PrettyData
import com.mordva.ui.widget.listItems.poster.StandardImageMedium
import com.mordva.ui.widget.other.RatingText

@Composable
fun MovieDetailCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        StandardImageMedium(movie.poster?.url)

        Spacer(modifier = Modifier.width(15.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            DetailInfoContent(
                title = movie.name ?: "",
                subtitle = "ConvertData.getAlternativeNameForMovie(movie)"
            )

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                GenreContent(movie.genres)

                OtherInfoContent(
                    rating = movie.rating?.kp ?: 0f,
                    votes = movie.votes?.kp ?: 0,
                    country = movie.countries
                )
            }
        }
    }
}

@Composable
private fun BoxScope.DetailInfoContent(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = Typography.bodyMedium.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = subtitle,
            fontWeight = FontWeight.Normal,
            fontSize = Typography.bodySmall.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun GenreContent(genre: List<ItemName>) {
    Text(
        text = genre.joinToString(", ") { it.name },
        fontWeight = FontWeight.Medium,
        fontSize = Typography.bodyMedium.fontSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun OtherInfoContent(
    rating: Float,
    votes: Int,
    country: List<ItemName>
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.weight(1f)) {
            RatingText(rating)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = PrettyData.getPrettyInt(votes),
                fontWeight = FontWeight.Normal,
                fontSize = Typography.bodySmall.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = country.joinToString(", ") { it.name },
            fontWeight = FontWeight.Normal,
            fontSize = Typography.bodyMedium.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().weight(2f)
        )
    }
}