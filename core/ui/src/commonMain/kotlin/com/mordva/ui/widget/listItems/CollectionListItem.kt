package com.mordva.ui.widget.listItems

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mordva.model.image.CollectionMovie
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.listItems.poster.SquareImage

@Composable
fun CollectionListItem(
    modifier: Modifier = Modifier,
    collectionMovie: CollectionMovie,
    size: Dp = 55.dp,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    ListItem(
        modifier = modifier,
        leadingContent = leadingIcon,
        headlineContent = {
            Row {
                SquareImage(
                    model = collectionMovie.cover?.url,
                    modifier = Modifier
                        .size(size)
                        .clip(RoundedCornerShape(5.dp))
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = collectionMovie.name.toString(),
                    fontSize = Typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}