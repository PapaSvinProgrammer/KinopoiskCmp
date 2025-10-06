package com.mordva.person.presentation.widget.listItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SpouseCard(
    modifier: Modifier = Modifier,
    name: String,
    photo: String? = null,
    countChild: Int? = null,
    divorced: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = onClick
            )
    ) {
        AsyncImage(
            model = photo,
            error = painterResource(Resources.Icons.Movie),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(60.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = name,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        countChild?.let {
            Text(
                text = "$countChild ребенка",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        if (divorced) {
            Text(
                text = "Разведены",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}