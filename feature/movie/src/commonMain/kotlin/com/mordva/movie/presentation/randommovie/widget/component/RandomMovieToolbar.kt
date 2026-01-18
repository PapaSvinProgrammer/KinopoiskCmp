package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.haze.hazeBorder
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun RandomMovieToolbar(
    onAction: (RandomMovieAction.BottomBarAction) -> Unit,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        CustomHorizontalFloatingToolbar(
            hazeState = hazeState,
            floatingActionButton = {
                IconButton(
                    onClick = { onAction(RandomMovieAction.BottomBarAction.SearchClicked) },
                    modifier = Modifier
                        .size(DsSpacer.M64)
                        .hazeBorder()
                        .hazeEffect(hazeState)
                ) {
                    Icon(
                        painter = painterResource(Icons.Search),
                        contentDescription = null,
                    )
                }
            },
            modifier = modifier.align(Alignment.Center)
        ) {
            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.StarClicked) }) {
                Icon(
                    painter = painterResource(Icons.Star),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.FavoriteClicked) }) {
                Icon(
                    painter = painterResource(Icons.BookmarkAdd),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.FilterClicked) }) {
                Icon(
                    painter = painterResource(Icons.Filter),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.MoreClicked) }) {
                Icon(
                    painter = painterResource(Icons.MoreHoriz),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
internal fun CustomHorizontalFloatingToolbar(
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DsSpacer.M4),
            modifier = Modifier
                .height(DsSpacer.M64)
                .hazeBorder()
                .hazeEffect(hazeState)
        ) { content() }

        Spacer(modifier = Modifier.width(DsSpacer.M10))

        floatingActionButton()
    }
}
