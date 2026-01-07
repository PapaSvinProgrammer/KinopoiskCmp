package com.mordva.movie.presentation.movie.widget.moreBottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.Strings
import com.mordva.ui.theme.Typography
import com.mordva.ui.util.ConvertData
import com.mordva.ui.widget.listItems.poster.StandardImageSmall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MoreBottomSheet(
    movie: Movie,
    isBlocked: Boolean,
    isViewed: Boolean,
    onDismissRequest: () -> Unit,
    onAction: (MoreSheetAction) -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        TopSheetContent(movie)
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()

        ListItem(
            modifier = Modifier.clickable {
                onAction(MoreSheetAction.AddInFolder)
                onDismissRequest()
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainerLow),
            leadingContent = {
                Icon(
                    painter = painterResource(Icons.NewFolder),
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = "Добавить в папку",
                    fontSize = Typography.bodyMedium.fontSize
                )
            }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 15.dp))

        ListItem(
            modifier = Modifier.clickable {
                onAction(MoreSheetAction.BlockedChange)
                onDismissRequest()
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainerLow),
            leadingContent = {
                Icon(
                    painter = if (isBlocked)
                        painterResource(Icons.StopFill)
                    else
                        painterResource(Icons.Stop),
                    tint = if (isBlocked)
                        MaterialTheme.colorScheme.primary
                    else
                        LocalContentColor.current,
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = stringResource(Strings.Blocked),
                    fontSize = Typography.bodyMedium.fontSize
                )
            }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 15.dp))

        ListItem(
            modifier = Modifier.clickable {
                onAction(MoreSheetAction.VisibilityChange)
                onDismissRequest()
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainerLow),
            leadingContent = {
                Icon(
                    painter = if (isViewed)
                        painterResource(Icons.VisibilityOff)
                    else
                        painterResource(Icons.Visibility),
                    tint = if (isViewed)
                        MaterialTheme.colorScheme.primary
                    else
                        LocalContentColor.current,
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = stringResource(Strings.Viewed),
                    fontSize = Typography.bodyMedium.fontSize
                )
            }
        )

//        DisableChangeStatusBarIconColor()
    }
}

@Composable
private fun TopSheetContent(movie: Movie) {
    Row(
        modifier = Modifier.padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StandardImageSmall(movie.poster?.url)

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = movie.name ?: "",
                fontSize = Typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = ConvertData.convertDateCreated(
                    year = movie.year,
                    start = movie.releaseYears.firstOrNull()?.start,
                    end = movie.releaseYears.firstOrNull()?.end,
                ),
                fontSize = Typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}