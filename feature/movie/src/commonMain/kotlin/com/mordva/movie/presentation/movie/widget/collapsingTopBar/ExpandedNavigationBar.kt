package com.mordva.movie.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ExpandedNavigationBar() {
    Row {
        NavigationTab(
            painter = painterResource(Resources.Icons.Star),
            title = stringResource(Resources.Strings.Evaluate)
        )

        NavigationTab(
            painter = painterResource(Resources.Icons.BookmarkAdd),
            title = stringResource(Resources.Strings.WillWatching)
        )

        NavigationTab(
            painter = painterResource(Resources.Icons.Share),
            title = stringResource(Resources.Strings.Share)
        )

        NavigationTab(
            painter = painterResource(Resources.Icons.MoreHoriz),
            title = stringResource(Resources.Strings.More)
        )
    }
}

@Composable
private fun NavigationTab(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String
) {
    Column(
        modifier = Modifier
            .height(45.dp)
            .width(90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier,
            painter = painter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = title,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}