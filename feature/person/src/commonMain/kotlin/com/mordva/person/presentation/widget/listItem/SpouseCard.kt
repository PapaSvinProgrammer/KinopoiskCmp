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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.ui.widget.listItems.poster.SquareImage

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
        SquareImage(
            model = photo,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
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