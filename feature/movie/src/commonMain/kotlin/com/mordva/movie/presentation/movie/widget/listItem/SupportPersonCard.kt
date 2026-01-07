package com.mordva.movie.presentation.movie.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.person.PersonMovie
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.listItems.poster.StandardImageSmall

@Composable
internal fun SupportPersonCard(
    modifier: Modifier = Modifier,
    person: PersonMovie,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(320.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StandardImageSmall(person.photo)

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = person.name ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = Typography.bodyMedium.fontSize
            )

            Text(
                text = person.profession ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = Typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}