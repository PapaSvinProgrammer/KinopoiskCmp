package com.mordva.ui.widget.listItems

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AwardListItem(
    modifier: Modifier,
    title: String,
    movieName: String?,
    winning: Boolean
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = Typography.bodyMedium.fontSize
                )

                movieName?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Medium,
                        fontSize = Typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = if (winning)
                        stringResource(Resources.Strings.Award)
                    else
                        stringResource(Resources.Strings.Nomination),
                    fontWeight = FontWeight.Medium,
                    fontSize = Typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        trailingContent = {
            Icon(
                painter = painterResource(Resources.Icons.KeyboardArrowRight),
                contentDescription = null
            )
        }
    )
}