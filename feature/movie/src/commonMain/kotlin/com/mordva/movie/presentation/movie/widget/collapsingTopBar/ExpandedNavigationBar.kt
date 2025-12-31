package com.mordva.movie.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ExpandedNavigationBar(
    customRating: Int? = null,
    isWillWatchPackage: Boolean = false,
    onEvaluate: () -> Unit,
    onAddIntoFuturePackage: () -> Unit,
    onShare: () -> Unit,
    onMore: () -> Unit
) {
    Row {
        NavigationTab(
            painter = if (customRating != null)
                painterResource(Resources.Icons.StarFill)
            else
                painterResource(Resources.Icons.Star),
            title = stringResource(Resources.Strings.Evaluate),
            clickable = onEvaluate,
            color = if (customRating != null)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )

        NavigationTab(
            painter = if (!isWillWatchPackage)
                painterResource(Resources.Icons.BookmarkAdd)
            else
                painterResource(Resources.Icons.BookmarkFill),
            title = stringResource(Resources.Strings.WillWatching),
            clickable = onAddIntoFuturePackage,
            color = if (isWillWatchPackage)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )

        NavigationTab(
            painter = painterResource(Resources.Icons.Share),
            title = stringResource(Resources.Strings.Share),
            clickable = onShare
        )

        NavigationTab(
            painter = painterResource(Resources.Icons.MoreHoriz),
            title = stringResource(Resources.Strings.More),
            clickable = onMore
        )
    }
}

@Composable
private fun NavigationTab(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    clickable: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .height(45.dp)
            .width(90.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = clickable
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier,
            painter = painter,
            contentDescription = null,
            tint = color
        )

        Text(
            text = title,
            fontSize = 10.sp
        )
    }
}