package com.mordva.ui.widget.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.Typography
import com.mordva.ui.util.PosterType
import com.mordva.ui.widget.chips.RatingChip
import com.mordva.ui.widget.listItems.poster.StandardImageLarge
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieFillCard(
    modifier: Modifier = Modifier,
    name: String,
    image: String,
    rating: Float? = null,
    top250: Int? = null,
    onClick: () -> Unit = {}
) {
    BasicContent(
        modifier = modifier,
        textModifier = Modifier.fillMaxWidth(),
        name = name,
        rating = rating,
        top250 = top250,
        onClick = onClick,
        posterContent = {
            AsyncImage(
                model = image,
                error = painterResource(Icons.Movie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(PosterType.STANDARD.ratio)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    )
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    name: String,
    image: String,
    rating: Float? = null,
    top250: Int? = null,
    onClick: () -> Unit = {}
) {
    BasicContent(
        modifier = modifier,
        textModifier = Modifier.width(140.dp),
        name = name,
        rating = rating,
        top250 = top250,
        onClick = onClick,
        posterContent = { StandardImageLarge(image) }
    )
}

@Composable
private fun BasicContent(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    name: String,
    rating: Float? = null,
    top250: Int? = null,
    onClick: () -> Unit = {},
    posterContent: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            posterContent()

            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                fontSize = Typography.bodySmall.fontSize,
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2,
                modifier = textModifier.padding(vertical = 10.dp)
            )
        }

        rating?.let { value ->
            RatingChip(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.dp),
                rating = value,
                top = top250
            )
        }
    }
}